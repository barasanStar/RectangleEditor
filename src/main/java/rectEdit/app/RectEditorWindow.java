package rectEdit.app;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import rectEdit.controller.RectEditorController;
import rectEdit.view.RectEditorView;
import rectEdit.view.menu.MenuBarBuilder;

public class RectEditorWindow {
	private final JFrame frame;
	private final RectEditorView view;
	private Image icon = new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();

	public RectEditorWindow(RectEditorController controller) {
		this.view = new RectEditorView(controller);
		frame = new JFrame("Rect Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view); // JPanelとして追加
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(900, 700);
		frame.setIconImage(icon);
		frame.setJMenuBar(MenuBarBuilder.build(controller));
	}

	public void show() {
		frame.setVisible(true);
	}

	public RectEditorView getView() {
		return view;
	}
}
