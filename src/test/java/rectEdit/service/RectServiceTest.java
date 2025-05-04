package rectEdit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectFactory;

public class RectServiceTest {
	private Rect rect;

	@BeforeEach
	void setUp() {
		RectFactory.resetIdCounterForTest(); // テスト用IDリセット
		rect = RectFactory.create(100, 100, 50, 60, Color.RED);
	}

	@Test
	void 色を変更できる() {
		Rect changed = RectService.changeColor(rect, Color.BLUE);
		assertEquals(Color.BLUE, changed.getColor());
		assertEquals(Color.RED, rect.getColor()); // 元の色は変わらない
		assertEquals(rect.getId(), changed.getId()); // IDは変わらない
		assertNotSame(rect, changed);
	}

	@Test
	void testMoveBy() {
		Rect moved = RectService.moveBy(rect, 20, -10);
		assertEquals(120, moved.getX());
		assertEquals(90, moved.getY());
		assertEquals(50, moved.getWidth()); // サイズは変わらない
		assertEquals(60, moved.getHeight());
		assertEquals(rect.getId(), moved.getId()); // IDは変わらない
	}

	@Test
	void testResizeByPositive() {
		Rect resized = RectService.resizeBy(rect, 30, 10);
		assertEquals(80, resized.getWidth());
		assertEquals(70, resized.getHeight());
		assertEquals(100, resized.getX()); // 位置は変わらない
		assertEquals(100, resized.getY());
	}

	@Test
	void testResizeByNegative() {
		Rect resized = RectService.resizeBy(rect, -10, -20);
		assertEquals(40, resized.getWidth());
		assertEquals(40, resized.getHeight());
	}

	@Test
	void testScaleUp() {
		Rect scaled = RectService.scale(rect, 1.5);
		assertEquals(75, scaled.getWidth()); // 50 * 1.5
		assertEquals(90, scaled.getHeight()); // 60 * 1.5
		assertEquals(100, scaled.getX()); // 位置は固定
		assertEquals(100, scaled.getY());
	}

	@Test
	void testScaleDown() {
		Rect scaled = RectService.scale(rect, 0.5);
		assertEquals(25, scaled.getWidth()); // 50 * 0.5
		assertEquals(30, scaled.getHeight()); // 60 * 0.5
	}

	@Test
	void testScaleZero() {
		Rect scaled = RectService.scale(rect, 0.0);
		assertEquals(0, scaled.getWidth());
		assertEquals(0, scaled.getHeight());
	}
	// ※ scale() に対して 負の倍率をエラーとしたい場合は、
	// IllegalArgumentException を投げて assertThrows() でテストします。

}
