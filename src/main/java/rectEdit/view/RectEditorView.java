package rectEdit.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import rectEdit.controller.RectEditorController;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectEditorModelListener;
import rectEdit.view.toolbar.ToolbarBuilder;

public class RectEditorView extends JPanel implements RectEditorModelListener {
	private final RectEditorModel model;
	private final BoardPanel boardPanel;
	private final RectListPanel rectListPanel;
	private final LogPanel logPanel;

	public RectEditorView(RectEditorModel model, RectEditorController controller) {
		this.model = model;
		setLayout(new BorderLayout());

		boardPanel = new BoardPanel(model);
		rectListPanel = new RectListPanel(model);
		logPanel = new LogPanel();

		model.addListener(rectListPanel);
		JToolBar toolBar = ToolbarBuilder.build(controller);
		add(toolBar, BorderLayout.NORTH);

		// 分割ビュー（左側：キャンバス、右側：長方形一覧）
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, rectListPanel);
		split.setDividerLocation(550); // 初期分割位置（左側550px）
		add(split, BorderLayout.CENTER);
		add(new JScrollPane(logPanel), BorderLayout.SOUTH);

		// アプリ起動時のボタン状態を設定。普段はモデル変更通知から呼ばれる。
		updateButtonState();

	}

	public void appendLog(String message) {
		logPanel.appendLog(message);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	@Override
	public void onRectsChanged(String operationLogMessage) {
		boardPanel.update(model.getRectanglesReadOnly(), model.getSelectionManager().getSelectedIds());
		rectListPanel.setRectangleList(model.getRectanglesReadOnly());
		updateButtonState();
		logPanel.appendLog("View#onRectsChanged: " + operationLogMessage);
	}

	// 上にある onRectsChanged との違いは、List<Rect>を更新するかどうかだけ。
	// 選択通知の方も、画面描画は行っている。
	// それであれば、いっそのこと、全部更新に統一しよう。
	// List<Rect>の更新処理を省くことのパフォーマンス上の良さは微々たるもの。
	// 一方、2つの分岐を統合して1つの更新処理とみなせることは見通しの良さを与える。
	@Override
	public void onSelectionChanged(String operationLogMessage) {
		boardPanel.updateSelectionOnly(model.getSelectionManager().getSelectedIds());
		updateButtonState();
		logPanel.appendLog("View#onSelectionChanged: " + operationLogMessage);
	}

	public void updateButtonState() {
		updateDeleteAllButton();
		updateSelectionDependentButtons(model.hasSelection());
		updateCreateButtons();
		updateUndoRedoButtons();
	}

	private void updateDeleteAllButton() {
		ToolbarBuilder.deleteAllButton.setEnabled(model.hasAnyRect());
	}

	private void updateSelectionDependentButtons(boolean hasSelection) {
		for (JButton btn : ToolbarBuilder.selectionDependentButtons) {
			btn.setEnabled(hasSelection);
		}
	}

	private void updateCreateButtons() {
		boolean belowSoftLimit = model.hasCapacity();
		for (JButton btn : ToolbarBuilder.createButtons) {
			btn.setEnabled(belowSoftLimit);
		}
	}

	private void updateUndoRedoButtons() {
		ToolbarBuilder.undoButton.setEnabled(model.canUndo());
		ToolbarBuilder.redoButton.setEnabled(model.canRedo());
	}
}
