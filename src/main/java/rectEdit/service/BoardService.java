package rectEdit.service;

import rectEdit.model.Board;
import rectEdit.model.Rect;

public class BoardService {

	private static final int MAX_WIDTH = 1000;
	private static final int MAX_HEIGHT = 1000;
	private static final int MAX_RECTS = 10;

	// Rectがボードの中に収まるかを判定
	public static boolean canPlace(Board board, Rect rect) {
		return rect.getX() >= 0 &&
				rect.getY() >= 0 &&
				rect.getX() + rect.getWidth() <= board.getWidth() &&
				rect.getY() + rect.getHeight() <= board.getHeight();
	}

	// ボードサイズ変更が可能かどうか（既存のRectすべてを対象に）
	public static boolean canResizeBoard(Board board, int newWidth, int newHeight) {
		if (newWidth > MAX_WIDTH || newHeight > MAX_HEIGHT) {
			return false;
		}
		for (Rect r : board.getRectangles()) {
			if (!canPlace(new Board(newWidth, newHeight), r)) {
				return false;
			}
		}
		return true;
	}

	public static boolean canAdd(Board board, Rect rect) {
		if (board.getRectangles().size() >= MAX_RECTS) {
			return false;
		}
		return canPlace(board, rect);
	}

	public static boolean addRectIfValid(Board board, Rect rect) {
		if (canAdd(board, rect)) {
			board.getRectangles().add(rect);
			return true;
		}
		return false;
	}

	public static void removeRect(Board board, int rectId) {
		board.getRectangles().removeIf(r -> r.getId() == rectId);
	}

	public static void clearBoard(Board board) {
		board.getRectangles().clear();
	}
}
