package rectangleEditor.handler.rect;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.RectEditorModel;

public class DeleteAllHandler implements ActionHandler {
	private final RectEditorModel model;

	public DeleteAllHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.pushSnapshot(); // Undo対応
		model.clearAllRects(); // モデル変更通知を含む
	}
}
