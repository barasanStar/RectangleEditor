package rectEdit.handler;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;

public class CreateRectAHandler implements ActionHandler {
	private final RectEditorModel model;

	public CreateRectAHandler(RectEditorModel model) {
		this.model = model;
	}

	@Override
	public void execute() {
		System.out.println("定型A、開始です！！");

		model.pushSnapshot(); // Undo/Redoのために、操作前にスナップショット保存
		Rect rect = RectFactory.create(5, 10, 100, 120, Color.BLUE);
		if (model.addRect(rect)) {
			model.selectOnly(rect.getId()); // 選択状態を更新
		} else {
			model.removeLatestSnapshot();
		}

	}
}
