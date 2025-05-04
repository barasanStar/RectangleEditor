package rectEdit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rectEdit.history.HistoryManager;
import rectEdit.service.BoardService;

public class RectEditorModel {
	private final Board board;
	private final SelectionManager selectionManager;
	private final HistoryManager historyManager;
	private final List<RectEditorModelListener> listeners = new ArrayList<>();

	public RectEditorModel(int width, int height) {
		this.board = new Board(width, height);
		this.selectionManager = new SelectionManager();
		this.historyManager = new HistoryManager(board, selectionManager);
	}

	// --- リスナー管理 ---
	public void addListener(RectEditorModelListener listener) {
		listeners.add(listener);
	}

	public void removeListener(RectEditorModelListener listener) {
		listeners.remove(listener);
	}

	private void notifyRectsChanged() {
		for (RectEditorModelListener listener : listeners) {
			listener.onRectsChanged();
		}
	}

	// --- Board操作の委譲 ---
	public boolean addRect(Rect rect) {
		if (BoardService.addRectIfValid(board, rect)) {
			notifyRectsChanged();
			return true;
		}
		return false;
	}

	public boolean removeRectById(int id) {
		if (BoardService.removeRectById(board, id)) {
			notifyRectsChanged();
			return true;
		}
		return false;
	}

	public boolean removeRect(Rect rect) {
		if (BoardService.removeRect(board, rect)) {
			notifyRectsChanged();
			return true;
		}
		return false;
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
			// 【TODO】何の通知を付けるべきか後ほど検討する。
			return true;
		}
		return false;
	}

	// --- 選択状態の取得・操作 ---
	public SelectionManager getSelectionManager() {
		return selectionManager;
	}

	public Set<Integer> getSelectedIds() {
		return selectionManager.getSelectedIds();
	}

	public void selectOnly(int id) {
		selectionManager.selectOnly(id);
	}

	// --- Undo/Redo ---
	public void pushSnapshot() {
		historyManager.pushSnapshot();
	}

	public void removeLatestSnapshot() {
		historyManager.removeLatestSnapshot();
	}

	public void undo() {
		historyManager.undo();
		notifyRectsChanged();
	}

	public void redo() {
		historyManager.redo();
		notifyRectsChanged();
	}

	public boolean canUndo() {
		return historyManager.canUndo();
	}

	public boolean canRedo() {
		return historyManager.canRedo();
	}

}
