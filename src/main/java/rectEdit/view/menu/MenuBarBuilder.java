package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;

public class MenuBarBuilder {
	public static JMenuBar build(RectEditorController controller) {
		JMenuBar menuBar = new JMenuBar();

		// ファイルメニュー
		JMenu fileMenu = new JMenu("ファイル");
		JMenuItem saveItem = new JMenuItem("ボードを保存");
		JMenuItem loadItem = new JMenuItem("ボードを開く");
		// saveItem.addActionListener(e -> controller.handleSaveBoard());
		// loadItem.addActionListener(e -> controller.handleLoadBoard());
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);

		// 操作メニュー
		JMenu actionMenu = new JMenu("操作");
		JMenuItem createAItem = new JMenuItem("定型長方形A作成");
		JMenuItem createBItem = new JMenuItem("定型長方形B作成");
		createAItem.addActionListener(e -> controller.handleCreateRectA());
		// createBItem.addActionListener(e -> controller.handleCreateRectB());
		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		// メニューバーに追加
		menuBar.add(fileMenu);
		menuBar.add(actionMenu);

		return menuBar;
	}
}
