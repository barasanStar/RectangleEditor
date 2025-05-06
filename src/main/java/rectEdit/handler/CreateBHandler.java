package rectEdit.handler;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;

public class CreateBHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateBHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.pushSnapshot(); // Undo対応
		Rect rect = RectFactory.create(50, 50, 80, 40, Color.GREEN);
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
