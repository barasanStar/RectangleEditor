package rectEdit.handler.rect;

import java.awt.Color;

import javax.swing.JColorChooser;

import rectEdit.handler.ActionHandler;
import rectEdit.model.RectEditorModel;
import rectEdit.view.RectEditorView;

public class ChangeColorHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public ChangeColorHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle() {
		if (!model.hasSelection())
			return;

		Color newColor = JColorChooser.showDialog(view, "色を選択", Color.BLACK);
		if (newColor == null)
			return; // キャンセル

		model.changeColorOfSelected(newColor);
	}
}