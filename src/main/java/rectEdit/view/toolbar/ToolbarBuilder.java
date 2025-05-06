package rectEdit.view.toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ToolbarBuilder {
	// 長方形が選択状態にある時に限り、有効化されるボタン群
	public static final List<JButton> selectionDependentButtons = new ArrayList<>();

	public static JToolBar build(RectEditorController controller) {
		JToolBar toolbar = new JToolBar();

		JButton undoButton = new JButton("◀");
		undoButton.addActionListener(e -> controller.handleUndo());
		toolbar.add(undoButton);

		JButton redoButton = new JButton("▶");
		redoButton.addActionListener(e -> controller.handleRedo());
		toolbar.add(redoButton);

		JButton createAButton = new JButton(ActionKey.CREATE_A);
		createAButton.addActionListener(e -> controller.handleCreateA());
		toolbar.add(createAButton);

		JButton createBButton = new JButton(ActionKey.CREATE_B);
		createBButton.addActionListener(e -> controller.handleCreateB());
		toolbar.add(createBButton);

		JButton createRandomButton = new JButton(ActionKey.CREATE_RANDOM);
		createRandomButton.addActionListener(e -> controller.handleCreateRandom());
		toolbar.add(createRandomButton);

		JButton deleteButton = new JButton(ActionKey.DELETE);
		deleteButton.addActionListener(e -> controller.handleDelete());
		toolbar.add(deleteButton);

		JButton deleteAllButton = new JButton(ActionKey.DELETE_ALL);
		deleteAllButton.addActionListener(e -> controller.handleDeleteAll());
		toolbar.add(deleteAllButton);

		JButton moveButton = new JButton(ActionKey.MOVE);
		moveButton.addActionListener(e -> controller.handleMove());
		toolbar.add(moveButton);

		JButton expandButton = new JButton(ActionKey.EXPAND);
		expandButton.addActionListener(e -> controller.handleExpand());
		toolbar.add(expandButton);

		JButton colorButton = new JButton(ActionKey.COLOR);
		colorButton.addActionListener(e -> controller.handleColor());
		toolbar.add(colorButton);

		selectionDependentButtons.add(deleteButton);
		selectionDependentButtons.add(moveButton);
		selectionDependentButtons.add(expandButton);
		selectionDependentButtons.add(colorButton);

		return toolbar;
	}
}
