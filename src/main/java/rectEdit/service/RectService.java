package rectEdit.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

/**
 * 既存の長方形に対する操作を定義することを責務とする。
 * 当該操作によって生成される長方形の論理的演算を責務とする。
 * @return Rect
 * 新しいインスタンスを返す。
 * 基本的には、IDは変化しない。（インターセクション操作を除く）
 * 
 * 制約はここでは考えないため、
 * 生成される長方形の適用可否については、別途判断が必要。
 */
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
		return new Rect(rect.getId(), rect.getX() + dx, rect.getY() + dy,
				rect.getWidth(), rect.getHeight(), rect.getColor());
	}

	/**
	 * 新座標(newX, newY)へ移動した長方形を返す
	 */
	public static Rect movePositionTo(Rect rect, int newX, int newY) {
		return new Rect(rect.getId(), newX, newY, rect.getWidth(), rect.getHeight(), rect.getColor());
	}

	/**
	 * 幅と高さの増減(dw, dh)だけ変化させた長方形を返す
	 */
	public static Rect resizeBy(Rect rect, int dw, int dh) {
		return new Rect(rect.getId(), rect.getX(), rect.getY(),
				rect.getWidth() + dw, rect.getHeight() + dh, rect.getColor());
	}

	/**
	 * 左上固定で拡大（あるいは縮小）した長方形を返す
	 */
	public static Rect scale(Rect rect, double factor) {
		if (factor < 0) {
			throw new IllegalArgumentException("拡大率は0.0より大きい必要があります: " + factor);
		}
		int newWidth = (int) Math.round(rect.getWidth() * factor);
		int newHeight = (int) Math.round(rect.getHeight() * factor);
		if (Math.min(newWidth, newHeight) < 1) {
			throw new IllegalArgumentException("辺の長さの計算結果が1以上になる拡大率である必要があります: " + factor);
		}
		return new Rect(rect.getId(), rect.getX(), rect.getY(), newWidth, newHeight, rect.getColor());
	}

	/**
	 * 左上固定で2軸それぞれ拡大（あるいは縮小）した長方形を返す
	 */
	public static Rect scaleTwoAxis(Rect rect, double factorX, double factorY) {
		if (factorX < 0) {
			throw new IllegalArgumentException("拡大率は0.0より大きい必要があります: " + factorX);
		}
		if (factorY < 0) {
			throw new IllegalArgumentException("拡大率は0.0より大きい必要があります: " + factorY);
		}
		int newWidth = (int) Math.round(rect.getWidth() * factorX);
		int newHeight = (int) Math.round(rect.getHeight() * factorY);
		if (newWidth < 1) {
			throw new IllegalArgumentException("辺の長さの計算結果が1以上になる拡大率である必要があります: " + factorX);
		}
		if (newHeight < 1) {
			throw new IllegalArgumentException("辺の長さの計算結果が1以上になる拡大率である必要があります: " + factorY);
		}
		return new Rect(rect.getId(), rect.getX(), rect.getY(), newWidth, newHeight, rect.getColor());
	}

	// 必要に応じて他の操作をここに追加できます。
	/**
	 * 選択された長方形を dx, dy 移動させる。
	 * すべての移動後の長方形がボード内に収まる場合のみ、移動を反映する。
	 * @return 移動できた場合 true、そうでない場合 false
	 */
	public static boolean moveSelectedRects(RectEditorModel model, int dx, int dy) {
		List<Rect> updatedRects = new ArrayList<>();
		Set<Integer> selectedIds = model.getSelectedIds();

		for (Rect rect : model.getRectanglesForMutation()) {
			if (selectedIds.contains(rect.getId())) {
				Rect moved = moveBy(rect, dx, dy);
				if (!model.getBoard().fitsWithinBoard(moved)) {
					return false; // 一つでもボード外に出るなら全体をキャンセル
				}
				updatedRects.add(moved);
			} else {
				updatedRects.add(rect); // 非選択はそのまま
			}
		}

		model.replaceAll(updatedRects); // 全体を置き換え
		return true;
	}
}
