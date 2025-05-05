package rectEdit.service;

import rectEdit.model.Board;
import rectEdit.model.Rect;

public class BoardService {

	// Rectがボードの中に収まるかを判定
	public static boolean canPlace(Board board, Rect rect) {
		return rect.getX() >= 0 &&
				rect.getY() >= 0 &&
				rect.getX() + rect.getWidth() <= board.getWidth() &&
				rect.getY() + rect.getHeight() <= board.getHeight();
	}

	// ボードサイズ変更が可能かどうか（既存Rectすべてをチェック）
	public static boolean canResizeBoard(Board board, int newWidth, int newHeight) {
		if (newWidth > Board.MAX_WIDTH || newHeight > Board.MAX_HEIGHT) {
			return false;
		}
		for (Rect r : board.getRectanglesReadOnly()) {
			if (!canPlace(new Board(newWidth, newHeight), r)) {
				return false;
			}
		}
		return true;
	}

	public static boolean canAdd(Board board, Rect rect) {
		if (board.isFull()) {
			return false;
		}
		return canPlace(board, rect);
	}

	public static boolean addRectIfValid(Board board, Rect rect) {
		if (canAdd(board, rect)) {
			board.addRect(rect);
			return true;
		}
		return false;
	}

	public static boolean removeRectById(Board board, int rectId) {
		return board.removeRectById(rectId);
	}

	public static boolean removeRect(Board board, Rect rect) {
		return board.removeRect(rect);
	}

	// 選択状態やハイライトもクリアする？
	public static void clearBoard(Board board) {
		board.clearAllRects();
	}
}
