package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class EditMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu editMenu = new JMenu("編集");

		JMenuItem undoItem = new JMenuItem(ActionKey.UNDO);
		JMenuItem redoItem = new JMenuItem(ActionKey.REDO);

		undoItem.addActionListener(e -> controller.handleUndo());
		redoItem.addActionListener(e -> controller.handleRedo());

		editMenu.add(undoItem);
		editMenu.add(redoItem);

		return editMenu;
	}
}
