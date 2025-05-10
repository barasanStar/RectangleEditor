package rectangleEditor.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;

import rectangleEditor.constants.Constants;
import rectangleEditor.controller.RectEditorController;
import rectangleEditor.model.RectEditorModel;

public class ShortcutKeyBinder {
	public static void bindShortcuts(RectEditorModel model, JRootPane rootPane, RectEditorController controller) {
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = rootPane.getActionMap();

		inputMap.put(Constants.SHORTCUT_UNDO, "undo");
		actionMap.put("undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleUndo();
			}
		});

		inputMap.put(Constants.SHORTCUT_REDO, "redo");
		actionMap.put("redo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleRedo();
			}
		});

		inputMap.put(Constants.SHORTCUT_DELETE, "delete");
		actionMap.put("delete", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleDelete();
			}
		});

		inputMap.put(Constants.SHORTCUT_ESC, "clearSelection");
		actionMap.put("clearSelection", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clearSelection();
			}
		});

		inputMap.put(Constants.SHORTCUT_LOAD, "loadFile");
		actionMap.put("loadFile", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleLoadFromText();
			}
		});

		inputMap.put(Constants.SHORTCUT_SAVE, "saveFile");
		actionMap.put("saveFile", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleSaveToText();
			}
		});
	}

}
