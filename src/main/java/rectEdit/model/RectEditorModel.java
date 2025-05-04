package rectEdit.model;

import java.util.List;

import rectEdit.history.HistoryManager;

public class RectEditorModel {
	private final Board board;
	private final SelectionManager selectionManager;
	private final HistoryManager historyManager;

	public RectEditorModel() {
		int width = 400;
		int height = 300;
		this.board = new Board(width, height);
		this.selectionManager = new SelectionManager();
		this.historyManager = new HistoryManager(board, selectionManager);
	}

	// --- Board操作の委譲 ---
	public void addRect(Rect rect) {
		board.addRect(rect);
	}

	public void removeRect(Rect rect) {
		board.removeRect(rect);
	}

	public void removeRectById(int id) {
		board.removeRectById(id);
	}

	public List<Rect> getRectangles() {
		return board.getRectangles();
	}

	public int getBoardWidth() {
		return board.getWidth();
	}

	public int getBoardHeight() {
		return board.getHeight();
	}

	// --- 選択状態の取得・操作 ---
	public SelectionManager getSelectionManager() {
		return selectionManager;
	}

	// --- Undo/Redo ---
	public void pushSnapshot() {
		historyManager.pushSnapshot();
	}

	public void undo() {
		historyManager.undo();
	}

	public void redo() {
		historyManager.redo();
	}

	public boolean canUndo() {
		return historyManager.canUndo();
	}

	public boolean canRedo() {
		return historyManager.canRedo();
	}

	public Board getBoard() {
		return board;
	}
}
