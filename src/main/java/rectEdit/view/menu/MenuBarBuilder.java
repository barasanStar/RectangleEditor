package rectEdit.view.menu;

import javax.swing.JMenuBar;

import rectEdit.controller.RectEditorController;
import rectEdit.model.RectEditorModel;

public class MenuBarBuilder {
	public static JMenuBar build(RectEditorModel model, RectEditorController controller) {
		JMenuBar menuBar = new JMenuBar();

		// 各カテゴリのメニューをメニューバーに追加
		menuBar.add(FileMenuBuilder.build(controller));
		menuBar.add(ActionMenuBuilder.build(controller));
		menuBar.add(ZOrderMenuBuilder.build(model, controller));

		return menuBar;
	}
}
