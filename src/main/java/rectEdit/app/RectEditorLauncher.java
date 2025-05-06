package rectEdit.app;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;
import rectEdit.handler.ChangeColorHandler;
import rectEdit.handler.CreateAHandler;
import rectEdit.handler.CreateBHandler;
import rectEdit.handler.CreateRandomHandler;
import rectEdit.handler.DeleteAllHandler;
import rectEdit.handler.DeleteHandler;
import rectEdit.handler.ExpandHandler;
import rectEdit.handler.HandlerRegistry;
import rectEdit.handler.LoadFromTextHandler;
import rectEdit.handler.MoveHandler;
import rectEdit.handler.SaveToTextHandler;
import rectEdit.model.RectEditorModel;

public class RectEditorLauncher {

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

			// ハンドラの登録
			registry.register(ActionKey.LOAD_FROM_TEXT, new LoadFromTextHandler(model, window.getView()));
			registry.register(ActionKey.SAVE_TO_TEXT, new SaveToTextHandler(model, window.getView()));
			registry.register(ActionKey.CREATE_A, new CreateAHandler(model));
			registry.register(ActionKey.CREATE_B, new CreateBHandler(model));
			registry.register(ActionKey.CREATE_RANDOM, new CreateRandomHandler(model));
			registry.register(ActionKey.DELETE, new DeleteHandler(model, window.getView()));
			registry.register(ActionKey.DELETE_ALL, new DeleteAllHandler(model));
			registry.register(ActionKey.MOVE, new MoveHandler(model, window.getView()));
			registry.register(ActionKey.EXPAND, new ExpandHandler(model, window.getView()));
			registry.register(ActionKey.COLOR, new ChangeColorHandler(model, window.getView()));

			// リスナー登録
			model.addListener(window.getView());

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
