package rectEdit.controller;

import rectEdit.handler.ActionKey;
import rectEdit.handler.HandlerRegistry;

public class RectEditorController {
	private final HandlerRegistry registry;

	public RectEditorController(HandlerRegistry registry) {
		this.registry = registry;
	}

	public void handleCreateA() {
		registry.get(ActionKey.CREATE_A).execute();
	}

	public void handleCreateB() {
		registry.get(ActionKey.CREATE_B).execute();
	}

	public void handleCreateRandom() {
		registry.get(ActionKey.CREATE_RANDOM).execute();
	}

	public void handleDelete() {
		registry.get(ActionKey.DELETE).execute();
	}

	public void handleDeleteAll() {
		registry.get(ActionKey.DELETE_ALL).execute();
	}

	public void handleMove() {
		registry.get(ActionKey.MOVE).execute();
	}

	public void handleExpand() {
		registry.get(ActionKey.EXPAND).execute();
	}

	public void handleColor() {
		registry.get(ActionKey.COLOR).execute();
	}

}
