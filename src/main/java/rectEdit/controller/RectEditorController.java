package rectEdit.controller;

import rectEdit.handler.HandlerRegistry;

public class RectEditorController {
	private final HandlerRegistry registry;

	public RectEditorController(HandlerRegistry registry) {
		this.registry = registry;
	}

	public void handleCreateRectA() {
		registry.get("createA").execute();
	}

}
