package rectEdit.service;

import java.awt.Color;

import rectEdit.model.Rect;

public class RectService {

	/**
	 * Rectを指定分だけ移動させる。
	 * 移動後もボード内に収まっている場合のみ移動。
	 */
	public static Rect moveIfWithinBounds(Rect rect, int dx, int dy, int boardWidth, int boardHeight) {
		int newX = rect.getX() + dx;
		int newY = rect.getY() + dy;

		if (newX < 0 || newY < 0 ||
				newX + rect.getWidth() > boardWidth ||
				newY + rect.getHeight() > boardHeight) {
			return rect; // はみ出すなら移動しない
		}

		return new Rect(rect.getId(), newX, newY, rect.getWidth(), rect.getHeight(), rect.getColor());
	}

	/**
	 * Rectのサイズを指定分だけ拡大・縮小。
	 * 0以下にならず、かつボード内に収まる場合のみ適用。
	 */
	public static Rect resizeIfValid(Rect rect, int dw, int dh, int boardWidth, int boardHeight) {
		int newWidth = rect.getWidth() + dw;
		int newHeight = rect.getHeight() + dh;

		if (newWidth <= 0 || newHeight <= 0)
			return rect;

		if (rect.getX() + newWidth > boardWidth || rect.getY() + newHeight > boardHeight) {
			return rect;
		}

		return new Rect(rect.getId(), rect.getX(), rect.getY(), newWidth, newHeight, rect.getColor());
	}

	/**
	 * Rectの色を変更した新しいインスタンスを返す。
	 * ルールなし。単純な変更。
	 */
	public static Rect changeColor(Rect rect, Color newColor) {
		return new Rect(rect.getId(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), newColor);
	}

	// 必要に応じて他の操作をここに追加できます。
}
