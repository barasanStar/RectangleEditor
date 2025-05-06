package rectEdit.view.toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import rectEdit.model.RectEditorModel;

public class ButtonStateManager {
	private List<JButton> selectionRequiredButtons = new ArrayList<>();
	private List<JButton> createButtons = new ArrayList<>();
	private JButton deleteAllButton;
	private JButton undoButton;
	private JButton redoButton;
	private RectEditorModel model;

	public ButtonStateManager(RectEditorModel model) {
		// 必要に応じて登録
		this.model = model;
	}

	// 登録系
	public void registerSelectionRequiredButton(JButton btn) {
		selectionRequiredButtons.add(btn);
	}

	public void registerCreateButton(JButton btn) {
		createButtons.add(btn);
	}

	public void setDeleteAllButton(JButton btn) {
		this.deleteAllButton = btn;
	}

	public void setUndoButton(JButton btn) {
		this.undoButton = btn;
	}

	public void setRedoButton(JButton btn) {
		this.redoButton = btn;
	}

	// 更新系
	public void updateAll() {
		updateSelectionRequiredButtons(model.hasSelection());
		updateCreateButtons(model.hasCapacity());
		updateDeleteAllButton(model.hasAnyRect());
		updateUndoRedoButtons(model.canUndo(), model.canRedo());
	}

	private void updateSelectionRequiredButtons(boolean hasSelection) {
		for (JButton btn : selectionRequiredButtons) {
			btn.setEnabled(hasSelection);
		}
	}

	private void updateCreateButtons(boolean hasCapacity) {
		for (JButton btn : createButtons) {
			btn.setEnabled(hasCapacity);
		}
	}

	private void updateDeleteAllButton(boolean hasAnyRect) {
		if (deleteAllButton != null) {
			deleteAllButton.setEnabled(hasAnyRect);
		}
	}

	private void updateUndoRedoButtons(boolean canUndo, boolean canRedo) {
		if (undoButton != null) {
			undoButton.setEnabled(canUndo);
		}
		if (redoButton != null) {
			redoButton.setEnabled(canRedo);
		}
	}
}
