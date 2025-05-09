package rectangleEditor.app;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;

import rectangleEditor.constants.Constants;
import rectangleEditor.controller.RectEditorController;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.view.RectEditorView;
import rectangleEditor.view.menu.MenuBarBuilder;

public class RectEditorWindow {
	private final JFrame frame;
	private final RectEditorModel model;
	private final RectEditorController controller;
	private final RectEditorView view;
	private Image icon = new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();

	public RectEditorWindow(RectEditorModel model, RectEditorController controller) {
		this.model = model;
		this.controller = controller;
		this.view = new RectEditorView(model, controller);
		frame = new JFrame("Rect Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view); // JPanelとして追加
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(1000, 700);
		frame.setIconImage(icon);
		frame.setJMenuBar(MenuBarBuilder.build(model, controller));

		setupShortcutKeys();
	}

	private void setupShortcutKeys() {
		InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = frame.getRootPane().getActionMap();

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

	}

	public void show() {
		frame.setVisible(true);
	}

	public RectEditorView getView() {
		return view;
	}

}
