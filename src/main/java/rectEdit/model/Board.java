package rectEdit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 長方形の保持・状態管理を責務とする。
 * ある長方形が本ボードに格納可能かどうかの判断や、
 * 格納操作指示は BoardServiceクラスに任せる！
 */
public class Board {
	private int width;
	private int height;
	private List<Rect> rectangles = new ArrayList<>();

	/**
	 * 本ボードに搭載可能な長方形個数の上限（変更可能）
	 */
	private int softLimit = 10;

	/**
	 * 全ボード共通。搭載可能な長方形個数の物理的上限（ハードリミット）
	 */
	private static final int MAX_RECT_CAPACITY = 20;

	// ボードの最大サイズ
	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGHT = 1000;

	// 【設計指針】起動時のボードサイズは決め打ちとする。
	public Board() {
		this.width = 500;
		this.height = 500;
	}

	public Board(int width, int height, List<Rect> loadRects) {
		if (width <= 0 || width > MAX_WIDTH || height <= 0 || height > MAX_HEIGHT) {
			throw new IllegalArgumentException("ボードサイズが制限を超えています");
		}
		this.width = width;
		this.height = height;
		rectangles = loadRects;
	}

	public Board(int width, int height) {
		if (width <= 0 || width > MAX_WIDTH || height <= 0 || height > MAX_HEIGHT) {
			throw new IllegalArgumentException("ボードサイズが制限を超えています");
		}
		this.width = width;
		this.height = height;
	}

	public boolean tryAddRect(Rect rect) {
		if (!hasCapacity()) {
			throw new IllegalStateException("長方形の上限を超えています");
		}
		if (!fitsWithinBoard(rect)) {
			throw new IllegalArgumentException("ボード外に長方形がはみ出しています");
		}
		rectangles.add(rect);
		return true;
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

	public void replaceAll(List<Rect> newRects) {
		rectangles.clear();
		rectangles.addAll(newRects);
	}

	// 長方形一覧（読み取り専用）
	public List<Rect> getRectanglesReadOnly() {
		return Collections.unmodifiableList(new ArrayList<>(rectangles));
	}

	// 長方形一覧（変更可能。サービス層専用）
	public List<Rect> getRectanglesForMutation() {
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

	public int getCurrentRectsCount() {
		return rectangles.size();
	}

	public int getSoftLimit() {
		return softLimit;
	}

	public int getMaxRectCapacity() {
		return MAX_RECT_CAPACITY;
	}

	// 上限を変更する（ただし物理上限以下）
	public void setRectLimit(int newLimit) {
		if (newLimit <= 0 || newLimit > MAX_RECT_CAPACITY) {
			throw new IllegalArgumentException("長方形の上限は 1〜" + MAX_RECT_CAPACITY + " までです");
		}
		this.softLimit = newLimit;
	}

	public boolean hasCapacity() {
		return rectangles.size() < softLimit;
	}

	public boolean fitsWithinBoard(Rect rect) {
		return rect.getX() + rect.getWidth() <= width &&
				rect.getY() + rect.getHeight() <= height;
	}

}
