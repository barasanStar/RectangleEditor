package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ActionMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu actionMenu = new JMenu("操作");

		JMenuItem createAItem = new JMenuItem(ActionKey.CREATE_A.getDisplayName());
		JMenuItem createBItem = new JMenuItem(ActionKey.CREATE_B.getDisplayName());
		JMenuItem createRandomItem = new JMenuItem(ActionKey.CREATE_RANDOM.getDisplayName());
		JMenuItem deleteItem = new JMenuItem(ActionKey.DELETE.getDisplayName());
		JMenuItem deleteAllItem = new JMenuItem(ActionKey.DELETE_ALL.getDisplayName());
		JMenuItem moveItem = new JMenuItem(ActionKey.MOVE.getDisplayName());
		JMenuItem expandItem = new JMenuItem(ActionKey.EXPAND.getDisplayName());
		JMenuItem colorItem = new JMenuItem(ActionKey.COLOR.getDisplayName());

		createAItem.addActionListener(e -> controller.handleCreateA());
		createBItem.addActionListener(e -> controller.handleCreateB());
		createRandomItem.addActionListener(e -> controller.handleCreateRandom());
		deleteItem.addActionListener(e -> controller.handleDelete());
		deleteAllItem.addActionListener(e -> controller.handleDeleteAll());
		moveItem.addActionListener(e -> controller.handleMove());
		expandItem.addActionListener(e -> controller.handleExpand());
		colorItem.addActionListener(e -> controller.handleColor());

		actionMenu.add(createAItem);
		actionMenu.add(createBItem);
		actionMenu.add(createRandomItem);
		actionMenu.add(deleteItem);
		actionMenu.add(deleteAllItem);
		actionMenu.add(moveItem);
		actionMenu.add(expandItem);
		actionMenu.add(colorItem);

		return actionMenu;
	}
}
