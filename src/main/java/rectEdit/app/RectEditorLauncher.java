package rectEdit.app;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;
import rectEdit.handler.CreateAActionHandler;
import rectEdit.handler.CreateBActionHandler;
import rectEdit.handler.CreateRandomActionHandler;
import rectEdit.handler.DeleteActionHandler;
import rectEdit.handler.DeleteAllActionHandler;
import rectEdit.handler.HandlerRegistry;
import rectEdit.handler.MoveActionHandler;
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
			registry.register(ActionKey.CREATE_A, new CreateAActionHandler(model));
			registry.register(ActionKey.CREATE_B, new CreateBActionHandler(model));
			registry.register(ActionKey.CREATE_RANDOM, new CreateRandomActionHandler(model));
			registry.register(ActionKey.DELETE, new DeleteActionHandler(model, window.getView()));
			registry.register(ActionKey.DELETE_ALL, new DeleteAllActionHandler(model));
			registry.register(ActionKey.MOVE, new MoveActionHandler(model, window.getView()));

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
