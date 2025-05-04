package rectEdit.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.view.BoardPanel;
import rectEdit.view.RectListPanel;

public class RectEditorWindow extends JFrame {
	private Image icon = new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();

	public RectEditorWindow() {
		super("Rect Editor");

		// 基本設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setIconImage(icon);

		// メニューバー（空）
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 分割ビュー（左：キャンバス、右：リスト）
		BoardPanel boardPanel = new BoardPanel();
		RectListPanel listPanel = new RectListPanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, listPanel);
		splitPane.setResizeWeight(0.7); // 左パネルにやや比重
		add(splitPane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			setGlobalUIFont(new Font("Meiryo", Font.BOLD, 14));
			RectEditorWindow window = new RectEditorWindow();
			window.setVisible(true);
		});
	}

	/**
	 * 全Swingコンポーネントのデフォルトフォントを統一する
	 */
	public static void setGlobalUIFont(Font font) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof Font) {
				UIManager.put(key, font);
			}
		}
	}
}
