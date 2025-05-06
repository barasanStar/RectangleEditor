package rectEdit.view.toolbar;

import javax.swing.JButton;
import javax.swing.JToolBar;

import rectEdit.constants.Constants;
import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;

public class ToolbarBuilder {

	public static JToolBar build(RectEditorController controller, ButtonStateManager buttonStateManager) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false); // ツールバーの固定

		// Undoボタン
		JButton undoButton = new JButton("◀");
		toolBar.add(undoButton);
		buttonStateManager.setUndoButton(undoButton);
		undoButton.addActionListener(e -> controller.handleUndo());
		undoButton.setToolTipText(Constants.TOOLTIP_UNDO);

		// Redoボタン
		JButton redoButton = new JButton("▶");
		toolBar.add(redoButton);
		buttonStateManager.setRedoButton(redoButton);
		redoButton.addActionListener(e -> controller.handleRedo());
		redoButton.setToolTipText(Constants.TOOLTIP_REDO);

		toolBar.addSeparator(); // 区切り

		// 作成ボタン（定型A）
		JButton createAButton = new JButton(ActionKey.CREATE_A);
		toolBar.add(createAButton);
		buttonStateManager.registerCreateButton(createAButton);
		createAButton.addActionListener(e -> controller.handleCreateA());

		// 作成ボタン（定型B）
		JButton createBButton = new JButton(ActionKey.CREATE_B);
		toolBar.add(createBButton);
		buttonStateManager.registerCreateButton(createBButton);
		createBButton.addActionListener(e -> controller.handleCreateB());

		// 作成ボタン（ランダム）
		JButton createRandomButton = new JButton(ActionKey.CREATE_RANDOM);
		toolBar.add(createRandomButton);
		buttonStateManager.registerCreateButton(createRandomButton);
		createRandomButton.addActionListener(e -> controller.handleCreateRandom());

		// 削除ボタン（選択中対象）
		JButton deleteButton = new JButton(ActionKey.DELETE);
		toolBar.add(deleteButton);
		buttonStateManager.registerSelectionRequiredButton(deleteButton);
		deleteButton.addActionListener(e -> controller.handleDelete());
		deleteButton.setToolTipText(Constants.TOOLTIP_DELETE);

		// 全削除ボタン
		JButton deleteAllButton = new JButton(ActionKey.DELETE_ALL);
		toolBar.add(deleteAllButton);
		buttonStateManager.setDeleteAllButton(deleteAllButton);
		deleteAllButton.addActionListener(e -> controller.handleDeleteAll());

		// 移動ボタン
		JButton moveButton = new JButton(ActionKey.MOVE);
		toolBar.add(moveButton);
		buttonStateManager.registerSelectionRequiredButton(moveButton);
		moveButton.addActionListener(e -> controller.handleMove());

		// 拡大・縮小ボタン
		JButton expandButton = new JButton(ActionKey.EXPAND);
		toolBar.add(expandButton);
		buttonStateManager.registerSelectionRequiredButton(expandButton);
		expandButton.addActionListener(e -> controller.handleExpand());

		// 色変更ボタン
		JButton colorButton = new JButton(ActionKey.COLOR);
		toolBar.add(colorButton);
		buttonStateManager.registerSelectionRequiredButton(colorButton);
		colorButton.addActionListener(e -> controller.handleColor());

		return toolBar;
	}

}
