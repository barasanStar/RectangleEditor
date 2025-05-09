package rectangleEditor.handler.edit;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.RectEditorModel;

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
