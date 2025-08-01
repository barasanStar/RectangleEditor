package rectangleEditor.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectangleEditor.constants.Constants;
import rectangleEditor.controller.RectEditorController;
import rectangleEditor.handler.ActionKey;

public class EditMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu editMenu = new JMenu("編集");

		JMenuItem undoMenuItem = new JMenuItem(ActionKey.UNDO);
		JMenuItem redoMenuItem = new JMenuItem(ActionKey.REDO);

		undoMenuItem.addActionListener(e -> controller.handleUndo());
		redoMenuItem.addActionListener(e -> controller.handleRedo());

		undoMenuItem.setAccelerator(Constants.SHORTCUT_UNDO);
		redoMenuItem.setAccelerator(Constants.SHORTCUT_REDO);

		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);

		return editMenu;
	}
}
