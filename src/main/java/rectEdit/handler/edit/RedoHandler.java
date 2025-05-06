package rectEdit.handler.edit;

import rectEdit.handler.ActionHandler;
import rectEdit.model.RectEditorModel;

public class RedoHandler implements ActionHandler {

	private final RectEditorModel model;

	public RedoHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void handle() {
		model.redo();
	}
}
