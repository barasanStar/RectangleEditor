package rectEdit.app;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;
import rectEdit.handler.HandlerRegistry;
import rectEdit.handler.edit.RedoHandler;
import rectEdit.handler.edit.UndoHandler;
import rectEdit.handler.file.LoadFromTextHandler;
import rectEdit.handler.file.SaveAsImageHandler;
import rectEdit.handler.file.SaveToTextHandler;
import rectEdit.handler.rect.ChangeColorHandler;
import rectEdit.handler.rect.CreateAHandler;
import rectEdit.handler.rect.CreateBHandler;
import rectEdit.handler.rect.CreateRandomHandler;
import rectEdit.handler.rect.DeleteAllHandler;
import rectEdit.handler.rect.DeleteHandler;
import rectEdit.handler.rect.ExpandHandler;
import rectEdit.handler.rect.MoveHandler;
import rectEdit.model.RectEditorModel;
import rectEdit.view.RectEditorView;

public class RectEditorLauncher {
	private static RectEditorView view;

	public static void launch() {
		SwingUtilities.invokeLater(() -> {
			setGlobalUIFont(new Font("Meiryo", Font.BOLD, 14));

			// モデルの生成（他はモデルに依存しているので、先に作る！）
			RectEditorModel model = new RectEditorModel();

			// ハンドラレジスタの生成
			HandlerRegistry registry = new HandlerRegistry();

			// コントローラの生成
			RectEditorController controller = new RectEditorController(registry);

			// View + Window の生成
			RectEditorWindow window = new RectEditorWindow(model, controller);
			view = window.getView();

			// ハンドラの登録(file)
			registry.register(ActionKey.LOAD_FROM_TEXT, new LoadFromTextHandler(model, view));
			registry.register(ActionKey.SAVE_TO_TEXT, new SaveToTextHandler(model, view));
			registry.register(ActionKey.SAVE_AS_IMAGE, new SaveAsImageHandler(view.getBoardPanel(), view));

			// ハンドラの登録(edit)
			registry.register(ActionKey.UNDO, new UndoHandler(model));
			registry.register(ActionKey.REDO, new RedoHandler(model));

			// ハンドラの登録(rect)
			registry.register(ActionKey.CREATE_A, new CreateAHandler(model));
			registry.register(ActionKey.CREATE_B, new CreateBHandler(model));
			registry.register(ActionKey.CREATE_RANDOM, new CreateRandomHandler(model));
			registry.register(ActionKey.DELETE, new DeleteHandler(model, view));
			registry.register(ActionKey.DELETE_ALL, new DeleteAllHandler(model));
			registry.register(ActionKey.MOVE, new MoveHandler(model, view));
			registry.register(ActionKey.EXPAND, new ExpandHandler(model, view));
			registry.register(ActionKey.COLOR, new ChangeColorHandler(model, view));

			// リスナー登録
			model.addListener(view);

			// 表示
			window.show();
		});
	}

	/**
	 * 全Swingコンポーネントのデフォルトフォントを統一する
	 */
	private static void setGlobalUIFont(Font font) {
		var keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof Font) {
				UIManager.put(key, font);
			}
		}
	}
}
