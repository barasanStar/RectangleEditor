package rectEdit.controller;

import rectEdit.handler.CreateRectAHandler;

public class RectEditorController {
	private final CreateRectAHandler createAHandler;
	//	private final CreateRectBHandler createBHandler;
	//	private final SaveBoardHandler saveHandler;
	//	private final LoadBoardHandler loadHandler;

	public RectEditorController(
			CreateRectAHandler createAHandler
	//			CreateRectBHandler createBHandler,
	//			SaveBoardHandler saveHandler,
	//			LoadBoardHandler loadHandler

	) {
		this.createAHandler = createAHandler;
		//		this.createBHandler = createBHandler;
		//		this.saveHandler = saveHandler;
		//		this.loadHandler = loadHandler;
	}

	public void handleCreateRectA() {
		createAHandler.execute();
	}

	//	public void handleCreateRectB() {
	//		createBHandler.execute();
	//	}
	//
	//	public void handleSaveBoard() {
	//		saveHandler.execute();
	//	}
	//
	//	public void handleLoadBoard() {
	//		loadHandler.execute();
	//	}
}
