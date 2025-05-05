package rectEdit.app;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;
import rectEdit.handler.CreateRectAHandler;
import rectEdit.handler.CreateRectBHandler;
import rectEdit.handler.HandlerRegistry;
import rectEdit.model.RectEditorModel;

public class RectEditorLauncher {

	public static void launch() {
		SwingUtilities.invokeLater(() -> {
			setGlobalUIFont(new Font("Meiryo", Font.BOLD, 14));

			// モデルの生成（他はモデルに依存しているので、先に作る！）
			RectEditorModel model = new RectEditorModel();

			// ハンドラ登録
			HandlerRegistry registry = new HandlerRegistry();

			// コントローラ
			RectEditorController controller = new RectEditorController(registry);

			// View + Window の生成
			RectEditorWindow window = new RectEditorWindow(model, controller);
			registry.register(ActionKey.CREATE_RECT_A, new CreateRectAHandler(model, window.getView()));
			registry.register(ActionKey.CREATE_RECT_B, new CreateRectBHandler(model, window.getView()));

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
