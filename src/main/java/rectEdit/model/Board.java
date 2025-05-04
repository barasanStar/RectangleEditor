package rectEdit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private int width;
	private int height;
	private final List<Rect> rectangles = new ArrayList<>();

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setSize(int newWidth, int newHeight) {
		this.width = newWidth;
		this.height = newHeight;
	}

	public List<Rect> getRectangles() {
		return rectangles;
	}

	public List<Rect> getRectanglesCopy() {
		return Collections.unmodifiableList(new ArrayList<>(rectangles));
	}
}
