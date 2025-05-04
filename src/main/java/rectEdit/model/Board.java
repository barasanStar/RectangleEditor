package rectEdit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	// ボードの最大サイズ
	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGHT = 1000;

	// 長方形の個数に関する制約
	private static final int MAX_RECT_CAPACITY = 20; // 物理的上限
	private int rectLimit = 10; // 現在の有効上限（変更可能）

	private int width;
	private int height;
	private List<Rect> rectangles = new ArrayList<>();

	public Board(int width, int height) {
		if (width <= 0 || width > MAX_WIDTH || height <= 0 || height > MAX_HEIGHT) {
			throw new IllegalArgumentException("ボードサイズが制限を超えています");
		}
		this.width = width;
		this.height = height;
		this.rectangles = new ArrayList<>();
	}

	// 長方形の追加（上限未チェック）
	public void addRect(Rect r) {
		rectangles.add(r);
	}

	/**
	 * 指定IDに一致するRectを返す。見つからなければnull。
	 */
	public Rect findById(int id) {
		for (Rect r : rectangles) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}

	/**
	 * 指定IDの長方形を削除する。
	 * 削除できたら true、見つからなければ false。
	 */
	public boolean removeRectById(int id) {
		return rectangles.removeIf(r -> r.getId() == id);
	}

	/**
	 * 指定長方形を削除する。
	 * 削除できたら true、見つからなければ false。
	 */
	public boolean removeRect(Rect rect) {
		return rectangles.remove(rect);
	}

	/**
	 * すべての長方形を削除する。
	 */
	public void clearAllRects() {
		rectangles.clear();
	}

	// 長方形一覧（書き換え防止コピー）
	public List<Rect> getRectanglesCopy() {
		return Collections.unmodifiableList(new ArrayList<>(rectangles));
	}

	// 直接リストにアクセスさせたい場合（サービス層専用）
	public List<Rect> getRectangles() {
		return rectangles;
	}

	// サイズ取得
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// サイズ変更（最大サイズ制約あり）
	public void setSize(int newWidth, int newHeight) {
		if (newWidth <= 0 || newWidth > MAX_WIDTH || newHeight <= 0 || newHeight > MAX_HEIGHT) {
			throw new IllegalArgumentException("サイズが最大制限を超えています");
		}
		this.width = newWidth;
		this.height = newHeight;
	}

	// 長方形上限チェック
	public boolean isFull() {
		return rectangles.size() >= rectLimit;
	}

	// 現在の有効上限を取得
	public int getCurrentLimit() {
		return rectLimit;
	}

	// 上限を変更する（ただし物理上限以下）
	public void setRectLimit(int newLimit) {
		if (newLimit <= 0 || newLimit > MAX_RECT_CAPACITY) {
			throw new IllegalArgumentException("長方形の上限は 1〜" + MAX_RECT_CAPACITY + " までです");
		}
		this.rectLimit = newLimit;
	}

	public int getMaxRectCapacity() {
		return MAX_RECT_CAPACITY;
	}

}
