package rectEdit.handler;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;
import rectEdit.view.RectEditorView;

public class CreateRectAHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public CreateRectAHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() {
		model.pushSnapshot(); // Undo/Redoのために、操作前にスナップショット保存
		Rect rect = RectFactory.create(5, 10, 100, 120, Color.BLUE);
		model.tryAddRect(rect); // 真の時、モデル変更通知。
	}
}
