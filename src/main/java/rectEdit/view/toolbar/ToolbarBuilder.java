package rectEdit.view.toolbar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ToolbarBuilder {

	public static JToolBar build(RectEditorController controller) {
		JToolBar toolbar = new JToolBar();

		JButton moveButton = new JButton(ActionKey.MOVE.getDisplayName());
		moveButton.addActionListener(e -> controller.handleMoveRect());
		toolbar.add(moveButton);

		return toolbar;
	}
}
