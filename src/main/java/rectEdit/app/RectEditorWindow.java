package rectEdit.app;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import rectEdit.controller.RectEditorController;
import rectEdit.model.RectEditorModel;
import rectEdit.view.RectEditorView;
import rectEdit.view.menu.MenuBarBuilder;

public class RectEditorWindow {
	private final JFrame frame;
	private final RectEditorView view;
	private Image icon = new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();

	public RectEditorWindow(RectEditorModel model, RectEditorController controller) {
		this.view = new RectEditorView(model, controller);
		frame = new JFrame("Rect Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view); // JPanelとして追加
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(1000, 700);
		frame.setIconImage(icon);
		frame.setJMenuBar(MenuBarBuilder.build(model, controller));

		// ESC キーで選択解除
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"clearSelection");
		frame.getRootPane().getActionMap().put("clearSelection", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clearSelection();
			}
		});

		// Undo (Ctrl+Z)
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK),
				"undo");
		frame.getRootPane().getActionMap().put("undo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleUndo();
			}
		});

		// Redo (Ctrl+Y)
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK),
				"redo");
		frame.getRootPane().getActionMap().put("redo", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleRedo();
			}
		});

		// Delete
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"deleteSelectedRectangles");
		frame.getRootPane().getActionMap().put("deleteSelectedRectangles", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleDelete();
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
