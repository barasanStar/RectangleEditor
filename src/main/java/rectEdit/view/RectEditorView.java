package rectEdit.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import rectEdit.controller.RectEditorController;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectEditorModelListener;

public class RectEditorView extends JPanel implements RectEditorModelListener {
	private final RectEditorModel model;
	private final BoardPanel boardPanel;
	private final RectListPanel rectListPanel;
	private final LogPanel logPanel;

	public RectEditorView(RectEditorModel model, RectEditorController controller) {
		this.model = model;
		setLayout(new BorderLayout());

		boardPanel = new BoardPanel(model);
		rectListPanel = new RectListPanel();
		logPanel = new LogPanel();

		// 分割ビュー（左側：キャンバス、右側：長方形一覧）
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, rectListPanel);
		split.setDividerLocation(550); // 初期分割位置（左側550px）
		add(split, BorderLayout.CENTER);
		add(new JScrollPane(logPanel), BorderLayout.SOUTH);
	}

	// Viewの更新APIなどもこちらに集約できる
	public void appendLog(String message) {
		logPanel.appendLog(message);
	}

	@Override
	public void onRectsChanged(String operationLogMessage) {
		boardPanel.update(model.getRectanglesReadOnly(), model.getSelectionManager().getSelectedIds());
		rectListPanel.setRectangleList(model.getRectanglesReadOnly());
		logPanel.appendLog(operationLogMessage);
	}

	@Override
	public void onSelectionChanged(String operationLogMessage) {
		logPanel.appendLog("■これはどこで呼ばれる？？■" + operationLogMessage);
		// 選択状態を更新。再描画。
		boardPanel.updateSelectionOnly(model.getSelectionManager().getSelectedIds());
	}
}
