package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class FileMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu fileMenu = new JMenu("ファイル");

		JMenuItem loadFromTextItem = new JMenuItem(ActionKey.LOAD_FROM_TEXT);
		JMenuItem saveToTextItem = new JMenuItem(ActionKey.SAVE_TO_TEXT);
		JMenuItem saveAsImageItem = new JMenuItem(ActionKey.SAVE_AS_IMAGE);

		loadFromTextItem.addActionListener(e -> controller.handleLoadFromText());
		saveToTextItem.addActionListener(e -> controller.handleSaveToText());
		saveAsImageItem.addActionListener(e -> controller.handleSaveAsImage());

		fileMenu.add(loadFromTextItem);
		fileMenu.add(saveToTextItem);
		fileMenu.addSeparator();
		fileMenu.add(saveAsImageItem);

		return fileMenu;
	}
}
