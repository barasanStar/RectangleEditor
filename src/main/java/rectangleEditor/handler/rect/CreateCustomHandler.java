package rectangleEditor.handler.rect;

import java.awt.Component;

import javax.swing.JOptionPane;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.model.RectFactory;
import rectangleEditor.view.dialog.CustomRectInput;
import rectangleEditor.view.dialog.InputDialogUtil;

public class CreateCustomHandler implements ActionHandler {
	private final RectEditorModel model;
	private final Component parent;

	public CreateCustomHandler(RectEditorModel model, Component parent) {
		this.model = model;
		this.parent = parent;
	}

	@Override
	public void handle() {
		CustomRectInput input = InputDialogUtil.showCustomRectInputDialog(parent);
		if (input == null)
			return; // ユーザーがキャンセル

		Rect rect = RectFactory.create(input.x(), input.y(), input.width(),
				input.height(), input.color());

		model.pushSnapshot(); // Undo対応
		boolean success = model.tryAddRect(rect);
		if (!success) {
			JOptionPane.showMessageDialog(parent, "ボードに収まりません。", "作成失敗", JOptionPane.WARNING_MESSAGE);
		}
	}
}
