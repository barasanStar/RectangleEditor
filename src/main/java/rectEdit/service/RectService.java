package rectEdit.service;

import java.awt.Color;

import rectEdit.model.Rect;

public class RectService {
	/**
	 * Rectの色を変更した新しいインスタンスを返す。
	 */
	public static Rect changeColor(Rect rect, Color newColor) {
		return new Rect(rect.getId(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), newColor);
	}

	/**
	 * 移動量(dx, dy)だけ移動した長方形を返す
	 */
	public static Rect moveBy(Rect rect, int dx, int dy) {
		return new Rect(rect.getId(),
				rect.getX() + dx,
				rect.getY() + dy,
				rect.getWidth(),
				rect.getHeight(),
				rect.getColor());
	}

	/**
	 * 幅と高さの増減(dw, dh)だけ変化させた長方形を返す
	 */
	public static Rect resizeBy(Rect rect, int dw, int dh) {
		return new Rect(rect.getId(),
				rect.getX(),
				rect.getY(),
				rect.getWidth() + dw,
				rect.getHeight() + dh,
				rect.getColor());
	}

	/**
	 * 左上固定で拡大縮小した長方形を返す
	 */
	public static Rect scale(Rect rect, double factor) {
		int newWidth = (int) Math.round(rect.getWidth() * factor);
		int newHeight = (int) Math.round(rect.getHeight() * factor);
		return new Rect(rect.getId(),
				rect.getX(),
				rect.getY(),
				newWidth,
				newHeight,
				rect.getColor());
	}

	// 必要に応じて他の操作をここに追加できます。
}
