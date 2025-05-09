package rectangleEditor.handler.rect;

import java.awt.Color;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.model.RectFactory;

public class CreateAHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateAHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.pushSnapshot(); // Undo対応
		Rect rect = RectFactory.create(5, 10, 100, 120, Color.BLUE);
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
