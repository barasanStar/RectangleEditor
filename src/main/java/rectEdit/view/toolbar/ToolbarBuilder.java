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

		JButton moveButton = new JButton(ActionKey.MOVE.getDisplayName());
		moveButton.addActionListener(e -> controller.handleMove());
		toolbar.add(moveButton);

		return toolbar;
	}
}
