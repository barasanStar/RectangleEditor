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

	public RectEditorModel() {
		this.board = new Board();
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

	private void notifyRectsChanged(String operationLogMessage) {
		for (RectEditorModelListener listener : listeners) {
			listener.onRectsChanged(operationLogMessage);
		}
	}

	private void notifySelectionChanged(String operationLogMessage) {
		for (RectEditorModelListener listener : listeners) {
			listener.onSelectionChanged(operationLogMessage);
		}
	}

	// --- Board操作の委譲 ---
	public boolean addRect(Rect rect) {
		if (BoardService.addRectIfValid(board, rect)) {
			notifyRectsChanged("[通知]長方形を追加: " + rect.toString());
			return true;
		}
		return false;
	}

	public boolean removeRectById(int id) {
		if (BoardService.removeRectById(board, id)) {
			notifyRectsChanged("[通知]長方形を削除。id: " + id);
			return true;
		}
		return false;
	}

	public boolean removeRect(Rect rect) {
		if (BoardService.removeRect(board, rect)) {
			notifyRectsChanged("[通知]長方形を削除: " + rect.toString());
			return true;
		}
		return false;
	}

	public List<Rect> getRectanglesReadOnly() {
		return board.getRectanglesReadOnly();
	}

	// 今の長方形個数を返す
	public int getCurrentRectsCount() {
		return board.getCurrentRectsCount();
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
		notifySelectionChanged("[通知]選択状態が変更。id: " + id);
	}

	// --- Undo/Redo ---
	public void pushSnapshot() {
		historyManager.pushSnapshot();
	}

	public void removeLatestSnapshot() {
		historyManager.removeLatestSnapshot();
	}

	public void undo() {
		if (!canUndo()) {
			return;
		}
		historyManager.undo();
		notifyRectsChanged("[通知]undoしました");
	}

	public void redo() {
		if (!canRedo()) {
			return;
		}
		historyManager.redo();
		notifyRectsChanged("[通知]redoしました");
	}

	public boolean canUndo() {
		return historyManager.canUndo();
	}

	public boolean canRedo() {
		return historyManager.canRedo();
	}

}
