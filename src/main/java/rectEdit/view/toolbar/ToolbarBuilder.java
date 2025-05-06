package rectEdit.view.toolbar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ToolbarBuilder {

	public static JToolBar build(RectEditorController controller) {
		JToolBar toolbar = new JToolBar();

		JButton createAButton = new JButton(ActionKey.CREATE_A.getDisplayName());
		createAButton.addActionListener(e -> controller.handleCreateA());
		toolbar.add(createAButton);

		JButton createBButton = new JButton(ActionKey.CREATE_B.getDisplayName());
		createBButton.addActionListener(e -> controller.handleCreateB());
		toolbar.add(createBButton);

		JButton createRandomButton = new JButton(ActionKey.CREATE_RANDOM.getDisplayName());
		createRandomButton.addActionListener(e -> controller.handleCreateRandom());
		toolbar.add(createRandomButton);

		JButton deleteButton = new JButton(ActionKey.DELETE.getDisplayName());
		deleteButton.addActionListener(e -> controller.handleDelete());
		toolbar.add(deleteButton);

		JButton deleteAllButton = new JButton(ActionKey.DELETE_ALL.getDisplayName());
		deleteAllButton.addActionListener(e -> controller.handleDeleteAll());
		toolbar.add(deleteAllButton);

		JButton moveButton = new JButton(ActionKey.MOVE.getDisplayName());
		moveButton.addActionListener(e -> controller.handleMove());
		toolbar.add(moveButton);

		return toolbar;
	}
}
