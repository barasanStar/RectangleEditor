package rectEdit.handler;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;
import rectEdit.view.RectEditorView;

public class CreateRectBHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public CreateRectBHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() {
		model.pushSnapshot(); // Undo/Redoのために、操作前にスナップショット保存
		Rect rect = RectFactory.create(50, 50, 100, 40, Color.GREEN);

		boolean added = model.addRect(rect);
		if (added) {
			model.selectOnly(rect.getId()); // 選択状態を更新

			// キャンバス再描画してほしいなぁ
			// 右側リスト更新してほしいなぁ
			// ログ表示してほしいなぁ

		} else {
			model.removeLatestSnapshot();
			view.appendLog("長方形Bの作成に失敗（範囲外）");
		}
	}

}
