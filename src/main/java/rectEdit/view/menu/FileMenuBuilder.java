package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;

public class FileMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu fileMenu = new JMenu("ファイル");

		JMenuItem saveItem = new JMenuItem("ボードを保存");
		JMenuItem loadItem = new JMenuItem("ボードを開く");

		// saveItem.addActionListener(e -> controller.handleSaveBoard());
		// loadItem.addActionListener(e -> controller.handleLoadBoard());

		fileMenu.add(saveItem);
		fileMenu.add(loadItem);

		return fileMenu;
	}
}
