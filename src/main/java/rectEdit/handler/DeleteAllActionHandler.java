package rectEdit.handler;

import rectEdit.model.RectEditorModel;

public class DeleteAllActionHandler implements ActionHandler {
	private final RectEditorModel model;

	public DeleteAllActionHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.pushSnapshot(); // Undo対応
		model.clearAllRects(); // モデル変更通知を含む
	}
}
