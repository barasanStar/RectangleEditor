package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class FileMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu fileMenu = new JMenu("ファイル");

		JMenuItem saveToTextItem = new JMenuItem(ActionKey.SAVE_TO_TEXT.getDisplayName());
		//		JMenuItem loadItem = new JMenuItem("ボードを開く");

		saveToTextItem.addActionListener(e -> controller.handleSaveToText());
		// loadItem.addActionListener(e -> controller.handleLoadBoard());

		fileMenu.add(saveToTextItem);
		//		fileMenu.add(loadItem);

		return fileMenu;
	}
}
