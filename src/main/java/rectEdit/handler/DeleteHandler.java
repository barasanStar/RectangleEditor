package rectEdit.handler;

import java.util.Set;

import rectEdit.model.RectEditorModel;
import rectEdit.view.RectEditorView;

public class DeleteHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public DeleteHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle() {
		Set<Integer> selected = model.getSelectedIds();
		if (selected.isEmpty()) {
			view.appendLog("削除対象が選択されていません。");
			return;
		}

		model.pushSnapshot(); // Undo対応

		int deletedCount = 0;
		for (int id : selected) {
			if (model.removeRectById(id)) {
				deletedCount++;
			}
		}

		model.getSelectionManager().clear(); // 削除後に選択状態をクリア
		view.appendLog(deletedCount + " 件の長方形を削除しました。");
	}

}
