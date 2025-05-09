package rectangleEditor.model;

import java.awt.Color;
import java.util.Random;

public class RectFactory {
	private static int nextId = 0;
	private static final Random random = new Random();

	public static Rect create(int x, int y, int width, int height, Color color) {
		return new Rect(nextId++, x, y, width, height, color);
	}

	public static Rect createRandom(int boardWidth, int boardHeight) {
		int minWidth = 20;
		int minHeight = 20;
		int maxWidth = Math.min(100, boardWidth);
		int maxHeight = Math.min(100, boardHeight);

		int width = minWidth + random.nextInt(maxWidth - minWidth + 1);
		int height = minHeight + random.nextInt(maxHeight - minHeight + 1);

		int x = random.nextInt(boardWidth - width + 1);
		int y = random.nextInt(boardHeight - height + 1);

		Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

		return create(x, y, width, height, color);
	}

	// テスト用：IDリセット
	public static void resetIdCounterForTest() {
		nextId = 0;
	}
}
