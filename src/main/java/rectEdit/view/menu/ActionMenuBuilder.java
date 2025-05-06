package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ActionMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu actionMenu = new JMenu("操作");

		JMenuItem createAItem = new JMenuItem(ActionKey.CREATE_A);
		JMenuItem createBItem = new JMenuItem(ActionKey.CREATE_B);
		JMenuItem createRandomItem = new JMenuItem(ActionKey.CREATE_RANDOM);
		JMenuItem deleteItem = new JMenuItem(ActionKey.DELETE);
		JMenuItem deleteAllItem = new JMenuItem(ActionKey.DELETE_ALL);
		JMenuItem moveItem = new JMenuItem(ActionKey.MOVE);
		JMenuItem expandItem = new JMenuItem(ActionKey.EXPAND);
		JMenuItem colorItem = new JMenuItem(ActionKey.COLOR);

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
		actionMenu.addSeparator();
		actionMenu.add(deleteItem);
		actionMenu.add(deleteAllItem);
		actionMenu.addSeparator();
		actionMenu.add(moveItem);
		actionMenu.add(expandItem);
		actionMenu.add(colorItem);

		return actionMenu;
	}
}
