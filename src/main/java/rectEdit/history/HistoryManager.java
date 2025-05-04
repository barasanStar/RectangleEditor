package rectEdit.history;

import java.util.ArrayDeque;
import java.util.Deque;

import rectEdit.model.Board;
import rectEdit.model.SelectionManager;

public class HistoryManager {

	private static final int MAX_HISTORY = 100;

	private final Deque<Snapshot> undoStack = new ArrayDeque<>();
	private final Deque<Snapshot> redoStack = new ArrayDeque<>();

	private final Board board;
	private final SelectionManager selection;

	public HistoryManager(Board board, SelectionManager selection) {
		this.board = board;
		this.selection = selection;
	}

	public void pushSnapshot() {
		undoStack.push(Snapshot.capture(board, selection));
		if (undoStack.size() > MAX_HISTORY) {
			undoStack.removeLast(); // 最古の履歴を削除
		}
		redoStack.clear(); // 新たな操作が入ったらRedoは無効
	}

	// 最新の履歴を削除（操作が失敗したときに呼ぶ）
	public void removeLatestSnapshot() {
		if (!undoStack.isEmpty()) {
			undoStack.pop();
		}
	}

	public boolean canUndo() {
		return !undoStack.isEmpty();
	}

	public boolean canRedo() {
		return !redoStack.isEmpty();
	}

	public void undo() {
		if (!canUndo()) {
			return;
		}
		Snapshot current = Snapshot.capture(board, selection);
		Snapshot prev = undoStack.pop();
		redoStack.push(current);
		prev.restoreTo(board, selection);
	}

	public void redo() {
		if (!canRedo()) {
			return;
		}
		Snapshot current = Snapshot.capture(board, selection);
		Snapshot next = redoStack.pop();
		undoStack.push(current);
		next.restoreTo(board, selection);
	}
}
