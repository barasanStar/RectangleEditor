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

		// 分割ビュー（左：キャンバス、右：リスト）
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, rectListPanel);
		split.setResizeWeight(0.7); // 左パネルにやや比重
		add(split, BorderLayout.CENTER);

		// 下部: ログ
		add(new JScrollPane(logPanel), BorderLayout.SOUTH);

	}

	// Viewの更新APIなどもこちらに集約できる
	public void appendLog(String message) {
		logPanel.appendLog(message);
	}

	@Override
	public void onRectsChanged() {
		// TODO 自動生成されたメソッド・スタブ
		// モデルの状態が変わったら画面を更新
		System.out.println("Rects変更の通知が飛びました");
		boardPanel.update(model.getRectangles());
		rectListPanel.setRectangleList(model.getRectangles());
	}

	@Override
	public void onSelectionChanged() {
		// TODO 自動生成されたメソッド・スタブ
		// 選択の状態が変わったら画面を更新？

	}

}
