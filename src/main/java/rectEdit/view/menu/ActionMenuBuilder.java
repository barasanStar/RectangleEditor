package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ActionMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu actionMenu = new JMenu("操作");

		JMenuItem createAItem = new JMenuItem(ActionKey.CREATE_RECT_A.getDisplayName());
		JMenuItem createBItem = new JMenuItem(ActionKey.CREATE_RECT_B.getDisplayName());

		createAItem.addActionListener(e -> controller.handleCreateRectA());
		createBItem.addActionListener(e -> controller.handleCreateRectB());

		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		return actionMenu;
	}
}
