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
		System.out.println("定型A、開始です！！");

		model.pushSnapshot(); // Undo/Redoのために、操作前にスナップショット保存
		Rect rect = RectFactory.create(5, 10, 100, 120, Color.BLUE);

		boolean added = model.addRect(rect);
		if (added) {
			model.selectOnly(rect.getId()); // 選択状態を更新

			// キャンバス再描画してほしいなぁ
			// view.updateBoard(model.getRectangles(), model.getBoard());
			hopeCanvasRepaint();

			// 右側リスト更新してほしいなぁ
			// view.updateRectListSelection(model.getSelectedIds());
			hopeRectListSelectionUpdate();

			// ログ表示してほしいなぁ
			view.appendLog("長方形Aを作成: " + rect);
		} else {
			model.removeLatestSnapshot();
			view.appendLog("長方形Aの作成に失敗（範囲外）");
		}
		System.out.println(rect);
	}

	private void hopeCanvasRepaint() {
	}

	private void hopeRectListSelectionUpdate() {
	}

}
