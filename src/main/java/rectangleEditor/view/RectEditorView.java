package rectangleEditor.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import rectangleEditor.controller.RectEditorController;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.model.RectEditorModelListener;
import rectangleEditor.view.toolbar.ButtonStateManager;
import rectangleEditor.view.toolbar.ToolbarBuilder;

public class RectEditorView extends JPanel implements RectEditorModelListener {
	private final RectEditorModel model;
	private final BoardPanel boardPanel;
	private final RectListPanel rectListPanel;
	private final LogPanel logPanel;
	private final ButtonStateManager buttonStateManager;

	public RectEditorView(RectEditorModel model, RectEditorController controller) {
		this.model = model;
		setLayout(new BorderLayout());

		this.buttonStateManager = new ButtonStateManager(model);

		boardPanel = new BoardPanel(model);
		rectListPanel = new RectListPanel(model);
		logPanel = new LogPanel();

		model.addListener(rectListPanel);
		JToolBar toolBar = ToolbarBuilder.build(controller, buttonStateManager);
		add(toolBar, BorderLayout.NORTH);

		// 分割ビュー（左側：キャンバス、右側：長方形一覧）
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, rectListPanel);
		split.setDividerLocation(550); // 初期分割位置（左側550px）
		add(split, BorderLayout.CENTER);
		add(new JScrollPane(logPanel), BorderLayout.SOUTH);

		// アプリ起動時のボタン状態を設定。普段はモデル変更通知から呼ばれる。
		buttonStateManager.updateAll();

	}

	public void appendLog(String message) {
		logPanel.appendLog(message);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	//	@Override
	//	public void onRectsChanged(String operationLogMessage) {
	//		boardPanel.update(model.getRectanglesReadOnly(), model.getSelectionManager().getSelectedIds());
	//		rectListPanel.setRectangleList(model.getRectanglesReadOnly());
	//		buttonStateManager.updateAll();
	//		logPanel.appendLog("View#onRectsChanged: " + operationLogMessage);
	//	}

	@Override
	public void onRectsChanged(String operationLogMessage) {
		List<Rect> rects = model.getRectanglesReadOnly();
		Set<Integer> selectedIds = model.getSelectionManager().getSelectedIds();

		log("★[View#onRectsChanged] selectedIds = ", selectedIds);
		log("★[View#onRectsChanged] rects = ", rects.stream().map(r -> r.getId()).toList());

		boardPanel.update(rects, selectedIds);
		rectListPanel.updateListAndSelection(rects, selectedIds); // ✅ 修正ポイント
		buttonStateManager.updateAll();

		if (!operationLogMessage.isEmpty()) {
			logPanel.appendLog("[View] " + operationLogMessage);
		}
	}

	private static void log(String tag, Object message) {
		System.out.println(
				"[" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
						+ "] " + tag + ": " + message);
	}

	// 上にある onRectsChanged との違いは、List<Rect>を更新するかどうかだけ。
	// 選択通知の方も、画面描画は行っている。
	// それであれば、いっそのこと、全部更新に統一しよう。
	// List<Rect>の更新処理を省くことのパフォーマンス上の良さは微々たるもの。
	// 一方、2つの分岐を統合して1つの更新処理とみなせることは見通しの良さを与える。
	@Override
	public void onSelectionChanged(String operationLogMessage) {
		boardPanel.updateSelectionOnly(model.getSelectionManager().getSelectedIds());
		buttonStateManager.updateAll();
		logPanel.appendLog("View#onSelectionChanged: " + operationLogMessage);
	}

}
