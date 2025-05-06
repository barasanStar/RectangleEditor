package rectEdit.handler.rect;

import javax.swing.JOptionPane;

import rectEdit.handler.ActionHandler;
import rectEdit.model.RectEditorModel;
import rectEdit.service.RectService;
import rectEdit.view.RectEditorView;

public class MoveHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public MoveHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle() {
		if (model.getSelectionManager().getSelectedIds().isEmpty()) {
			view.appendLog("移動対象が選択されていません");
			return;
		}

		String dxStr = JOptionPane.showInputDialog(view, "dx を入力してください");
		String dyStr = JOptionPane.showInputDialog(view, "dy を入力してください");
		if (dxStr == null || dyStr == null) {
			view.appendLog("移動がキャンセルされました");
			return;
		}

		try {
			int dx = Integer.parseInt(dxStr.trim());
			int dy = Integer.parseInt(dyStr.trim());

			boolean success = RectService.moveSelectedRects(model, dx, dy);
			if (success) {
				view.appendLog("選択中の長方形を移動しました");
			} else {
				view.appendLog("移動後の長方形がボード外にはみ出すため、移動できませんでした");
			}
		} catch (NumberFormatException e) {
			view.appendLog("数値の形式が正しくありません");
		}
	}
}
