package rectEdit.view.toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolBar;

import rectEdit.constants.Constants;
import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ToolbarBuilder {
	// 長方形が選択状態にある時に限り、有効化されるボタン群
	public static final List<JButton> selectionDependentButtons = new ArrayList<>();
	public static final List<JButton> createButtons = new ArrayList<>();
	public static JButton deleteAllButton;
	public static JButton undoButton;
	public static JButton redoButton;

	public static JToolBar build(RectEditorController controller) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		undoButton = new JButton("◀");
		redoButton = new JButton("▶");
		JButton createAButton = new JButton(ActionKey.CREATE_A);
		JButton createBButton = new JButton(ActionKey.CREATE_B);
		JButton createRandomButton = new JButton(ActionKey.CREATE_RANDOM);
		JButton deleteButton = new JButton(ActionKey.DELETE);
		deleteAllButton = new JButton(ActionKey.DELETE_ALL);
		JButton moveButton = new JButton(ActionKey.MOVE);
		JButton expandButton = new JButton(ActionKey.EXPAND);
		JButton colorButton = new JButton(ActionKey.COLOR);

		undoButton.addActionListener(e -> controller.handleUndo());
		redoButton.addActionListener(e -> controller.handleRedo());
		createAButton.addActionListener(e -> controller.handleCreateA());
		createBButton.addActionListener(e -> controller.handleCreateB());
		createRandomButton.addActionListener(e -> controller.handleCreateRandom());
		deleteButton.addActionListener(e -> controller.handleDelete());
		deleteAllButton.addActionListener(e -> controller.handleDeleteAll());
		moveButton.addActionListener(e -> controller.handleMove());
		expandButton.addActionListener(e -> controller.handleExpand());
		colorButton.addActionListener(e -> controller.handleColor());

		toolBar.add(undoButton);
		toolBar.add(redoButton);
		toolBar.addSeparator();
		toolBar.add(createAButton);
		toolBar.add(createBButton);
		toolBar.add(createRandomButton);
		toolBar.add(deleteButton);
		toolBar.add(deleteAllButton);
		toolBar.add(moveButton);
		toolBar.add(expandButton);
		toolBar.add(colorButton);

		undoButton.setToolTipText(Constants.TOOLTIP_UNDO);
		redoButton.setToolTipText(Constants.TOOLTIP_REDO);
		deleteButton.setToolTipText(Constants.TOOLTIP_DELETE);

		registerCreateButton(createAButton);
		registerCreateButton(createBButton);
		registerCreateButton(createRandomButton);

		registerSelectionDependentButton(deleteButton);
		registerSelectionDependentButton(moveButton);
		registerSelectionDependentButton(expandButton);
		registerSelectionDependentButton(colorButton);

		return toolBar;
	}

	public static void registerSelectionDependentButton(JButton btn) {
		selectionDependentButtons.add(btn);
	}

	public static void registerCreateButton(JButton btn) {
		createButtons.add(btn);
	}

}
