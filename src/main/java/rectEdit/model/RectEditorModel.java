package rectEdit.model;

import java.util.List;

import rectEdit.history.HistoryManager;
import rectEdit.service.BoardService;

public class RectEditorModel {
	private final Board board;
	private final SelectionManager selectionManager;
	private final HistoryManager historyManager;

	public RectEditorModel(int width, int height) {
		this.board = new Board(width, height);
		this.selectionManager = new SelectionManager();
		this.historyManager = new HistoryManager(board, selectionManager);
	}

	// --- Board操作の委譲 ---
	public boolean addRect(Rect rect) {
		return BoardService.addRectIfValid(board, rect);
	}

	public boolean removeRectById(int id) {
		return BoardService.removeRectById(board, id);
	}

	public boolean removeRect(Rect rect) {
		return BoardService.removeRect(board, rect);
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

	public Board getBoard() {
		return board;
	}

	public boolean setBoardSize(int newWidth, int newHeight) {
		if (BoardService.canResizeBoard(board, newWidth, newHeight)) {
			board.setSize(newWidth, newHeight);
			return true;
		}
		return false;
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

}
