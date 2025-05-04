package rectEdit.app;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import rectEdit.controller.RectEditorController;
import rectEdit.view.BoardPanel;
import rectEdit.view.RectListPanel;

public class RectEditorWindow extends JFrame {
	private Image icon = new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();
	private final RectEditorController controller;
	private final BoardPanel boardPanel;
	private final RectListPanel listPanel;

	public RectEditorWindow(RectEditorController controller) {
		super("Rect Editor");

		// 必要なら今後 Controller/Model を引数で受け取れるようにしてもよい
		this.controller = controller;
		this.boardPanel = new BoardPanel();
		this.listPanel = new RectListPanel();

		setupWindow();
		setupMenuBar();
		setupLayout();
	}

	// 基本設定
	private void setupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setIconImage(icon);
	}

	// 分割ビュー（左：キャンバス、右：リスト）
	private void setupLayout() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, listPanel);
		splitPane.setResizeWeight(0.7); // 左パネルにやや比重
		add(splitPane, BorderLayout.CENTER);
	}

	// メニューバー
	private void setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// ファイルメニュー
		JMenu fileMenu = new JMenu("ファイル");
		JMenuItem saveItem = new JMenuItem("ボードを保存");
		JMenuItem loadItem = new JMenuItem("ボードを開く");
		//		saveItem.addActionListener(e -> controller.handleSaveBoard());
		//		loadItem.addActionListener(e -> controller.handleLoadBoard());
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);

		// 操作メニュー
		JMenu actionMenu = new JMenu("操作");
		JMenuItem createAItem = new JMenuItem("定型長方形A作成");
		JMenuItem createBItem = new JMenuItem("定型長方形B作成");
		createAItem.addActionListener(e -> controller.handleCreateRectA());
		//		createBItem.addActionListener(e -> controller.handleCreateRectB());
		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		// メニューバーに追加
		menuBar.add(fileMenu);
		menuBar.add(actionMenu);

		// フレームに設定
		setJMenuBar(menuBar);
	}

}
