package rectEdit.handler;

import rectEdit.model.RectEditorModel;

public class CreateRectAHandler {
	private final RectEditorModel model;

	public CreateRectAHandler(RectEditorModel model) {
		this.model = model;
	}

	public void execute() {
		//		model.addStandardRectA(); // 定型A作成処理
		System.out.println("定型Aです！");
	}
}
