package rectangleEditor.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectangleEditor.controller.RectEditorController;
import rectangleEditor.handler.ActionKey;

public class ActionMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu actionMenu = new JMenu("操作");

		JMenuItem createAMenuItem = new JMenuItem(ActionKey.CREATE_A);
		JMenuItem createBMenuItem = new JMenuItem(ActionKey.CREATE_B);
		JMenuItem createRandomMenuItem = new JMenuItem(ActionKey.CREATE_RANDOM);
		JMenuItem deleteMenuItem = new JMenuItem(ActionKey.DELETE);
		JMenuItem deleteAllMenuItem = new JMenuItem(ActionKey.DELETE_ALL);
		JMenuItem moveMenuItem = new JMenuItem(ActionKey.MOVE);
		JMenuItem expandMenuItem = new JMenuItem(ActionKey.EXPAND);
		JMenuItem colorMenuItem = new JMenuItem(ActionKey.COLOR);

		createAMenuItem.addActionListener(e -> controller.handleCreateA());
		createBMenuItem.addActionListener(e -> controller.handleCreateB());
		createRandomMenuItem.addActionListener(e -> controller.handleCreateRandom());
		deleteMenuItem.addActionListener(e -> controller.handleDelete());
		deleteAllMenuItem.addActionListener(e -> controller.handleDeleteAll());
		moveMenuItem.addActionListener(e -> controller.handleMove());
		expandMenuItem.addActionListener(e -> controller.handleExpand());
		colorMenuItem.addActionListener(e -> controller.handleColor());

		actionMenu.add(createAMenuItem);
		actionMenu.add(createBMenuItem);
		actionMenu.add(createRandomMenuItem);
		actionMenu.addSeparator();
		actionMenu.add(deleteMenuItem);
		actionMenu.add(deleteAllMenuItem);
		actionMenu.addSeparator();
		actionMenu.add(moveMenuItem);
		actionMenu.add(expandMenuItem);
		actionMenu.add(colorMenuItem);

		return actionMenu;
	}
}
