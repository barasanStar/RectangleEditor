package rectEdit.handler.rect;

import javax.swing.JOptionPane;

import rectEdit.handler.ActionHandler;
import rectEdit.model.RectEditorModel;
import rectEdit.service.RectService;
import rectEdit.view.RectEditorView;

public class ExpandHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;

	public ExpandHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void handle() {
		if (model.getSelectionManager().getSelectedIds().isEmpty()) {
			view.appendLog("拡大・縮小対象が選択されていません");
			return;
		}

		String scaleStr = JOptionPane.showInputDialog(view, "scale を入力してください");
		if (scaleStr == null) {
			view.appendLog("拡大・縮小がキャンセルされました");
			return;
		}

		try {
			double scale = Double.parseDouble(scaleStr.trim());

			boolean success = RectService.expandSelectedRects(model, scale);
			if (success) {
				view.appendLog("選択中の長方形を拡大・縮小しました");
			} else {
				view.appendLog("移動後の長方形がボード外にはみ出すため、拡大・縮小できませんでした");
			}
		} catch (NumberFormatException e) {
			view.appendLog("数値の形式が正しくありません");
		}
	}
}
