package rectEdit.handler.edit;

import rectEdit.handler.ActionHandler;
import rectEdit.model.RectEditorModel;

public class UndoHandler implements ActionHandler {

	private final RectEditorModel model;

	public UndoHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.undo();
	}
}
