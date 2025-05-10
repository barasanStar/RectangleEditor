package rectangleEditor.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectangleEditor.constants.Constants;
import rectangleEditor.controller.RectEditorController;
import rectangleEditor.handler.ActionKey;

public class FileMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu fileMenu = new JMenu("ファイル");

		JMenuItem loadFromTextMenuItem = new JMenuItem(ActionKey.LOAD_FROM_TEXT);
		JMenuItem saveToTextMenuItem = new JMenuItem(ActionKey.SAVE_TO_TEXT);
		JMenuItem saveAsImageMenuItem = new JMenuItem(ActionKey.SAVE_AS_IMAGE);

		loadFromTextMenuItem.addActionListener(e -> controller.handleLoadFromText());
		saveToTextMenuItem.addActionListener(e -> controller.handleSaveToText());
		saveAsImageMenuItem.addActionListener(e -> controller.handleSaveAsImage());

		fileMenu.add(loadFromTextMenuItem);
		fileMenu.add(saveToTextMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveAsImageMenuItem);

		loadFromTextMenuItem.setAccelerator(Constants.SHORTCUT_LOAD);
		saveToTextMenuItem.setAccelerator(Constants.SHORTCUT_SAVE);

		return fileMenu;
	}
}
