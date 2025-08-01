package rectangleEditor.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rectangleEditor.history.HistoryManager;
import rectangleEditor.service.BoardService;
import rectangleEditor.service.RectService;

public class RectEditorModel {
	private Board board;
	private final BoardService boardService;
	private final SelectionManager selectionManager;
	private final HistoryManager historyManager;
	private final List<RectEditorModelListener> listeners = new ArrayList<>();

	public RectEditorModel() {
		this.board = new Board();
		this.boardService = new BoardService();
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

	public void notifyRectsChanged(String operationLogMessage) {
		for (RectEditorModelListener listener : listeners) {
			listener.onRectsChanged(operationLogMessage);
		}
	}

	public void notifySelectionChanged(String operationLogMessage) {
		for (RectEditorModelListener listener : listeners) {
			listener.onSelectionChanged(operationLogMessage);
		}
	}

	// --- Board操作の委譲 ---
	public boolean tryAddRect(Rect rect) {
		try {
			boolean added = boardService.tryAddRect(board, rect);
			if (added) {
				selectionManager.selectOnly(rect.getId()); // 選択状態を更新
				notifyRectsChanged("[通知]長方形を追加: " + rect.toString());
				return true;
			} else {
				removeLatestSnapshot();
				System.out.println("長方形Aの作成に失敗（範囲外）"); // ViewではなくListenerで通知すべき
			}
		} catch (IllegalArgumentException ex1) {
			System.out.println("ボード外に長方形がはみ出しています"); // 【TODO】操作ログ、MsgBox
		} catch (IllegalStateException ex2) {
			System.out.println("長方形個数上限に達しています");
		}
		return false;
	}

	public boolean removeRectById(int id) {
		if (boardService.removeRectById(board, id)) {
			selectionManager.remove(id);
			notifyRectsChanged("[通知]長方形を削除。id: " + id);
			return true;
		}
		return false;
	}

	public boolean removeRect(Rect rect) {
		if (boardService.removeRect(board, rect)) {
			notifyRectsChanged("[通知]長方形を削除: " + rect.toString());
			return true;
		}
		return false;
	}

	public boolean fitsWithinBoard(Board board, Rect rect) {
		return boardService.fitsWithinBoard(board, rect);
	}

	public List<Rect> getRectanglesReadOnly() {
		return board.getRectanglesReadOnly();
	}

	public List<Rect> getRectanglesForMutation() {
		return board.getRectanglesForMutation();
	}

	public void replaceAll(List<Rect> newRects) {
		board.replaceAll(newRects);
		notifyRectsChanged("全長方形を置き換え");
	}

	public void clearAllRects() {
		boardService.clearAllRects(board);
		selectionManager.clear();
		notifyRectsChanged("全長方形を削除");
	}

	public void changeColorOfSelected(Color color) {
		for (int id : selectionManager.getSelectedIds()) {
			Rect rect = board.getRectanglesForMutation().get(id);
			Rect colorChanged = RectService.changeColor(rect, color);
			board.getRectanglesForMutation().set(id, colorChanged);
		}
		notifyRectsChanged("色を変更");
	}

	public boolean hasAnyRect() {
		return board.hasAnyRect();
	}

	public boolean hasCapacity() {
		return board.hasCapacity();
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
		if (boardService.canResizeBoard(board, newWidth, newHeight)) {
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

	public void setSelectedIds(Set<Integer> newIds) {
		if (selectionManager.setSelectedIds(newIds)) {
			notifySelectionChanged("選択状態を適用しました");
		}
	}

	public void removeSelection(int id) {
		if (selectionManager.remove(id)) {
			notifySelectionChanged("id:" + id + "を選択解除しました");
		}
	}

	public void toggleSelection(int id) {
		if (selectionManager.toggle(id)) {
			notifySelectionChanged("選択変更しました（トグル）");
		}
	}

	public void clearSelection() {
		if (selectionManager.clear()) {
			notifySelectionChanged("選択全解除しました");
		}
	}

	public void selectOnly(int id) {
		if (selectionManager.selectOnly(id)) {
			notifySelectionChanged("id:" + id + "を選択しました");
		}
	}

	public boolean hasSelection() {
		return selectionManager.hasSelection();
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
		notifyRectsChanged("Undo を実行しました。");
	}

	public void redo() {
		if (!canRedo()) {
			return;
		}
		historyManager.redo();
		notifyRectsChanged("Redo を実行しました。");
	}

	public boolean canUndo() {
		return historyManager.canUndo();
	}

	public boolean canRedo() {
		return historyManager.canRedo();
	}

	public void loadBoard(Board newBoard) {
		this.board = newBoard;
		selectionManager.clear();
		historyManager.clear();
		notifyRectsChanged("");
	}
}
