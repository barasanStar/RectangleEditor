package rectEdit.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import rectEdit.controller.RectEditorController;

public class RectEditorView extends JPanel {

	private final BoardPanel boardPanel;
	private final RectListPanel rectListPanel;
	private final LogPanel logPanel;

	public RectEditorView(RectEditorController controller) {
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

		// イベント登録などは controller を使ってここで行う
	}

	// Viewの更新APIなどもこちらに集約できる
	public void appendLog(String message) {
		logPanel.appendLog(message);
	}

}
