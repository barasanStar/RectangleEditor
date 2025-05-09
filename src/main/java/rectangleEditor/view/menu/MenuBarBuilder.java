package rectangleEditor.view.menu;

import javax.swing.JMenuBar;

import rectangleEditor.controller.RectEditorController;
import rectangleEditor.model.RectEditorModel;

public class MenuBarBuilder {
	public static JMenuBar build(RectEditorModel model, RectEditorController controller) {
		JMenuBar menuBar = new JMenuBar();

		// 各カテゴリのメニューをメニューバーに追加
		menuBar.add(FileMenuBuilder.build(controller));
		menuBar.add(EditMenuBuilder.build(controller));
		menuBar.add(ActionMenuBuilder.build(controller));
		menuBar.add(ZOrderMenuBuilder.build(model, controller));

		return menuBar;
	}
}
