package rectangleEditor.model;

import java.awt.Color;
import java.awt.Point;
import java.util.Objects;

/**
 * 長方形を定義する責務。
 */
public final class Rect {
	private final int id;
	private final int x, y, width, height;
	private final Color color;

	public Rect(int id, int x, int y, int width, int height, Color color) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public boolean contains(Point p) {
		return p.x >= x && p.x <= x + width &&
				p.y >= y && p.y <= y + height;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Rect rect = (Rect) obj;
		return id == rect.id; // IDベースで等価判定
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Rect[id:" + id + "]{x:" + x + ",y:" + y + ",w:" + width + ",h:" + height +
				",RGB(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")}";
	}
}
