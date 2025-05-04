package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;

public class ActionMenuBuilder {
	public static JMenu build(RectEditorController controller) {
		JMenu actionMenu = new JMenu("操作");

		JMenuItem createAItem = new JMenuItem("定型長方形A作成");
		JMenuItem createBItem = new JMenuItem("定型長方形B作成");

		createAItem.addActionListener(e -> controller.handleCreateRectA());
		// createBItem.addActionListener(e -> controller.handleCreateRectB());

		actionMenu.add(createAItem);
		actionMenu.add(createBItem);

		return actionMenu;
	}
}
