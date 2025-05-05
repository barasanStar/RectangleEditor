package rectEdit.handler;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;

public class CreateAActionHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateAActionHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.pushSnapshot(); // Undo対応
		Rect rect = RectFactory.create(5, 10, 100, 120, Color.BLUE);
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
