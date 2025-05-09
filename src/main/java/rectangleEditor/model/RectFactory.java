package rectangleEditor.model;

import java.awt.Color;
import java.util.Random;

public class RectFactory {
	private static int nextId = 0;
	private static final Random random = new Random();

	public static Rect create(int x, int y, int width, int height, Color color) {
		return new Rect(nextId++, x, y, width, height, color);
	}

	public static Rect createRandom() {
		int x = random.nextInt(400);
		int y = random.nextInt(300);
		int w = 50 + random.nextInt(100);
		int h = 50 + random.nextInt(100);
		Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		return create(x, y, w, h, color);
	}

	// テスト用：IDリセット
	public static void resetIdCounterForTest() {
		nextId = 0;
	}
}
