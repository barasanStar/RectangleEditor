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

		boardPanel = new BoardPanel();
		rectListPanel = new RectListPanel();
		logPanel = new LogPanel();

		// 分割ビュー（左側：キャンバス、右側：長方形一覧）
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				boardPanel, rectListPanel);
		//		split.setResizeWeight(0.6); // 左パネルにやや比重
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
		// モデルRects変更の際、このメソッド呼んでいます。
		boardPanel.update(model.getRectanglesReadOnly());
		rectListPanel.setRectangleList(model.getRectanglesReadOnly());
		logPanel.appendLog(operationLogMessage);
	}

	@Override
	public void onSelectionChanged() {
		// TODO 自動生成されたメソッド・スタブ
		// 選択の状態が変わったら画面を更新？

	}
}
