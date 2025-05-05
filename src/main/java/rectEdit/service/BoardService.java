package rectEdit.service;

import rectEdit.model.Board;
import rectEdit.model.Rect;

/**
 * 長方形がボードに格納可能かどうかの判断や、格納操作指示を責務とする。
 * ボード単独では完結しないような、ボードに関する事柄を責務とする。
 */
public class BoardService {

	// 【TODO】選択状態やハイライトもクリアする？ -> もちろん！
	public void clearBoard(Board board) {
		board.clearAllRects();
	}

	public boolean fitsWithinBoard(Board board, Rect rect) {
		return board.fitsWithinBoard(rect);
	}

	public boolean tryAddRect(Board board, Rect rect) {
		return board.tryAddRect(rect);
	}

	public boolean removeRectById(Board board, int rectId) {
		return board.removeRectById(rectId);
	}

	public boolean removeRect(Board board, Rect rect) {
		return board.removeRect(rect);
	}

	// ボードサイズ変更が可能かどうか（既存Rectすべてをチェック）
	public boolean canResizeBoard(Board board, int newWidth, int newHeight) {
		if (newWidth > Board.MAX_WIDTH || newHeight > Board.MAX_HEIGHT) {
			return false;
		}

		if (newWidth <= 0 || newWidth > Board.MAX_WIDTH || newHeight <= 0 || newHeight > Board.MAX_HEIGHT) {
			throw new IllegalArgumentException("サイズが最大制限を超えています");
		}

		for (Rect r : board.getRectanglesReadOnly()) {
			if (!new Board(newWidth, newHeight).fitsWithinBoard(r)) {
				return false;
			}
		}
		return true;
	}

}
