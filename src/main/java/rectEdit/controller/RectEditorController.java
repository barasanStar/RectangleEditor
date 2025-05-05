package rectEdit.controller;

import rectEdit.handler.ActionKey;
import rectEdit.handler.HandlerRegistry;

public class RectEditorController {
	private final HandlerRegistry registry;

	public RectEditorController(HandlerRegistry registry) {
		this.registry = registry;
	}

	public void handleCreateRectA() {
		registry.get(ActionKey.CREATE_RECT_A).execute();
	}

	public void handleCreateRectB() {
		registry.get(ActionKey.CREATE_RECT_B).execute();
	}

}
