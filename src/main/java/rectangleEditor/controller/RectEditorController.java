package rectangleEditor.controller;

import rectangleEditor.handler.ActionKey;
import rectangleEditor.handler.HandlerRegistry;

public class RectEditorController {
	private final HandlerRegistry registry;

	public RectEditorController(HandlerRegistry registry) {
		this.registry = registry;
	}

	//--------------------- ファイル --------------------- 
	public void handleLoadFromText() {
		registry.get(ActionKey.LOAD_FROM_TEXT).handle();
	}

	public void handleSaveToText() {
		registry.get(ActionKey.SAVE_TO_TEXT).handle();
	}

	public void handleSaveAsImage() {
		registry.get(ActionKey.SAVE_AS_IMAGE).handle();
	}

	//--------------------- 編集 --------------------- 
	public void handleUndo() {
		registry.get(ActionKey.UNDO).handle();
	}

	public void handleRedo() {
		registry.get(ActionKey.REDO).handle();
	}

	//--------------------- 操作 --------------------- 
	public void handleCreateA() {
		registry.get(ActionKey.CREATE_A).handle();
	}

	public void handleCreateB() {
		registry.get(ActionKey.CREATE_B).handle();
	}

	public void handleCreateRandom() {
		registry.get(ActionKey.CREATE_RANDOM).handle();
	}

	public void handleDelete() {
		registry.get(ActionKey.DELETE).handle();
	}

	public void handleDeleteAll() {
		registry.get(ActionKey.DELETE_ALL).handle();
	}

	public void handleMove() {
		registry.get(ActionKey.MOVE).handle();
	}

	public void handleExpand() {
		registry.get(ActionKey.EXPAND).handle();
	}

	public void handleColor() {
		registry.get(ActionKey.COLOR).handle();
	}

}
