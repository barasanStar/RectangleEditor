package rectangleEditor.app;

import java.awt.Font;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectangleEditor.constants.Constants;
import rectangleEditor.controller.RectEditorController;
import rectangleEditor.handler.ActionKey;
import rectangleEditor.handler.HandlerRegistry;
import rectangleEditor.handler.edit.RedoHandler;
import rectangleEditor.handler.edit.UndoHandler;
import rectangleEditor.handler.file.LoadFromTextHandler;
import rectangleEditor.handler.file.SaveAsImageHandler;
import rectangleEditor.handler.file.SaveToTextHandler;
import rectangleEditor.handler.rect.ChangeColorHandler;
import rectangleEditor.handler.rect.CreateAHandler;
import rectangleEditor.handler.rect.CreateBHandler;
import rectangleEditor.handler.rect.CreateRandomHandler;
import rectangleEditor.handler.rect.DeleteAllHandler;
import rectangleEditor.handler.rect.DeleteHandler;
import rectangleEditor.handler.rect.ExpandHandler;
import rectangleEditor.handler.rect.MoveHandler;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.view.RectEditorView;

public class RectEditorLauncher {
	public static File baseDir = new File(Constants.BASE_DIRECTORY_NAME);

	public static void launch() {
		SwingUtilities.invokeLater(() -> {
			setGlobalUIFont(new Font("Meiryo", Font.BOLD, 14));

			// 他はモデルに依存しているため、最初にモデルを生成する！
			RectEditorModel model = new RectEditorModel();
			HandlerRegistry registry = new HandlerRegistry();
			RectEditorController controller = new RectEditorController(registry);
			RectEditorWindow window = new RectEditorWindow(model, controller);

			makeBaseDir();
			registerFileHandlers(registry, model, window.getView(), baseDir);
			registerEditHandlers(registry, model);
			registerRectHandlers(registry, model, window.getView());

			model.addListener(window.getView());
			window.show();
		});
	}

	private static void makeBaseDir() {
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}
	}

	private static void registerFileHandlers(HandlerRegistry registry, RectEditorModel model,
			RectEditorView view, File baseDir) {
		registry.register(ActionKey.LOAD_FROM_TEXT, new LoadFromTextHandler(model, view, baseDir));
		registry.register(ActionKey.SAVE_TO_TEXT, new SaveToTextHandler(model, view, baseDir));
		registry.register(ActionKey.SAVE_AS_IMAGE, new SaveAsImageHandler(view.getBoardPanel(), view, baseDir));
	}

	private static void registerEditHandlers(HandlerRegistry registry, RectEditorModel model) {
		registry.register(ActionKey.UNDO, new UndoHandler(model));
		registry.register(ActionKey.REDO, new RedoHandler(model));
	}

	private static void registerRectHandlers(HandlerRegistry registry, RectEditorModel model, RectEditorView view) {
		registry.register(ActionKey.CREATE_A, new CreateAHandler(model));
		registry.register(ActionKey.CREATE_B, new CreateBHandler(model));
		registry.register(ActionKey.CREATE_RANDOM, new CreateRandomHandler(model));
		registry.register(ActionKey.DELETE, new DeleteHandler(model, view));
		registry.register(ActionKey.DELETE_ALL, new DeleteAllHandler(model));
		registry.register(ActionKey.MOVE, new MoveHandler(model, view));
		registry.register(ActionKey.EXPAND, new ExpandHandler(model, view));
		registry.register(ActionKey.COLOR, new ChangeColorHandler(model, view));
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
