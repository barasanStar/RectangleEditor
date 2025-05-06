package rectEdit.handler;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;

public class CreateRandomActionHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateRandomActionHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.pushSnapshot(); // Undo対応
		Rect rect = RectFactory.createRandom();
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
