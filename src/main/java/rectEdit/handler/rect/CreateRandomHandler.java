package rectEdit.handler.rect;

import rectEdit.handler.ActionHandler;
import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;

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
