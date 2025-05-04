package rectEdit.app;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import rectEdit.controller.RectEditorController;
import rectEdit.view.BoardPanel;
import rectEdit.view.RectListPanel;
import rectEdit.view.menu.MenuBarBuilder;

public class RectEditorWindow {
	private final JFrame frame;
	private final BoardPanel boardPanel;
	private final RectListPanel listPanel;

	public RectEditorWindow(RectEditorController controller) {
		frame = new JFrame("Rect Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(loadIcon());

		boardPanel = new BoardPanel();
		listPanel = new RectListPanel();

		setupLayout();
		// メニューバーを構築
		frame.setJMenuBar(MenuBarBuilder.build(controller));
	}

	// 分割ビュー（左：キャンバス、右：リスト）
	private void setupLayout() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, listPanel);
		splitPane.setResizeWeight(0.7); // 左パネルにやや比重
		frame.add(splitPane, BorderLayout.CENTER);
	}

	private Image loadIcon() {
		return new ImageIcon(RectEditorWindow.class.getResource("/icon.png")).getImage();
	}

	public void show() {
		frame.setVisible(true);
	}

}
