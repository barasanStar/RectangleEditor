package rectangleEditor.handler.edit;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.RectEditorModel;

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
