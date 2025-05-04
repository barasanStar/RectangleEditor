package rectEdit.app;

import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionHandler;
import rectEdit.handler.CreateRectAHandler;
import rectEdit.model.RectEditorModel;

public class RectEditorLauncher {

	public static void launch() {
		SwingUtilities.invokeLater(() -> {
			setGlobalUIFont(new Font("Meiryo", Font.BOLD, 14));

			// モデルの生成（他はモデルに依存しているので、先に作る！）
			RectEditorModel model = new RectEditorModel();

			// ハンドラの生成
			ActionHandler createRectAHandler = new CreateRectAHandler(model);

			// コントローラの生成
			RectEditorController controller = new RectEditorController(createRectAHandler);

			// ウィンドウの生成（コントローラを渡す）
			RectEditorWindow window = new RectEditorWindow(controller);
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
