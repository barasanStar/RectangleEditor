package rectEdit.app;

import java.awt.Font;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.constants.Constants;
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
