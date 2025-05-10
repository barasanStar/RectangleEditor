package rectangleEditor.app;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import rectangleEditor.controller.RectEditorController;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.view.RectEditorView;
import rectangleEditor.view.ShortcutKeyBinder;
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
		frame = new JFrame("Rectangle Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view); // JPanelとして追加
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(1000, 700);
		frame.setIconImage(icon);
		frame.setJMenuBar(MenuBarBuilder.build(model, controller));

		ShortcutKeyBinder.bindShortcuts(model, frame.getRootPane(), controller);
	}

	public void show() {
		frame.setVisible(true);
	}

	public RectEditorView getView() {
		return view;
	}

}
