package rectEdit.controller;

import rectEdit.handler.ActionKey;
import rectEdit.handler.HandlerRegistry;

public class RectEditorController {
	private final HandlerRegistry registry;

	public RectEditorController(HandlerRegistry registry) {
		this.registry = registry;
	}

	public void handleSaveToText() {
		registry.get(ActionKey.SAVE_TO_TEXT).handle();
	}

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
