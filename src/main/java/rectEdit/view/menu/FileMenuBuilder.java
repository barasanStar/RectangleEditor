package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class FileMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu fileMenu = new JMenu("ファイル");

		JMenuItem saveToTextItem = new JMenuItem(ActionKey.SAVE_TO_TEXT.getDisplayName());
		JMenuItem loadFromTextItem = new JMenuItem(ActionKey.LOAD_FROM_TEXT.getDisplayName());

		saveToTextItem.addActionListener(e -> controller.handleSaveToText());
		loadFromTextItem.addActionListener(e -> controller.handleLoadFromText());

		fileMenu.add(saveToTextItem);
		fileMenu.add(loadFromTextItem);

		return fileMenu;
	}
}
