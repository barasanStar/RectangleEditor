package rectangleEditor.handler.rect;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.model.RectFactory;

public class CreateRandomHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateRandomHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.pushSnapshot(); // Undo対応
		Rect rect = RectFactory.createRandom();
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
