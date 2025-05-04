package rectEdit.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
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

		// メニューバー
		createMenuBar();

		// 分割ビュー（左：キャンバス、右：リスト）
		BoardPanel boardPanel = new BoardPanel();
		RectListPanel listPanel = new RectListPanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, listPanel);
		splitPane.setResizeWeight(0.7); // 左パネルにやや比重
		add(splitPane, BorderLayout.CENTER);
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// ファイルメニュー
		JMenu fileMenu = new JMenu("ファイル");
		JMenuItem saveItem = new JMenuItem("ボードを保存");
		JMenuItem loadItem = new JMenuItem("ボードを開く");
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);

		// 操作メニュー
		JMenu actionMenu = new JMenu("操作");
		JMenuItem createAItem = new JMenuItem("定型長方形A作成");
		JMenuItem createBItem = new JMenuItem("定型長方形B作成");
		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		// メニューバーに追加
		menuBar.add(fileMenu);
		menuBar.add(actionMenu);

		// フレームに設定
		setJMenuBar(menuBar);
	}

	private void createMenuBar(RectEditorController controller) {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("ファイル");
		JMenuItem saveItem = new JMenuItem("ボードを保存");
		JMenuItem loadItem = new JMenuItem("ボードを開く");
		//		saveItem.addActionListener(e -> controller.handleSaveBoard());
		//		loadItem.addActionListener(e -> controller.handleLoadBoard());
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);

		JMenu actionMenu = new JMenu("操作");
		JMenuItem createAItem = new JMenuItem("定型長方形A作成");
		JMenuItem createBItem = new JMenuItem("定型長方形B作成");
		createAItem.addActionListener(e -> controller.handleCreateRectA());
		//		createBItem.addActionListener(e -> controller.handleCreateRectB());
		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		menuBar.add(fileMenu);
		menuBar.add(actionMenu);
		setJMenuBar(menuBar);
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
