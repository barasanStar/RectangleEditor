package rectEdit.view.menu;

import javax.swing.JMenuBar;

import rectEdit.controller.RectEditorController;

public class MenuBarBuilder {
	public static JMenuBar build(RectEditorController controller) {
		JMenuBar menuBar = new JMenuBar();
		// 各カテゴリのメニューをメニューバーに追加
		menuBar.add(FileMenuBuilder.build(controller));
		menuBar.add(ActionMenuBuilder.build(controller));
		return menuBar;
	}
}
