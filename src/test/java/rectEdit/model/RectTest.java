package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RectTest {

	@BeforeEach
	void setUp() {
		// 毎テスト前にIDを初期化
		RectFactory.resetIdCounterForTest();
	}

	@Test
	void RectFactoryで仕様通りの長方形が作成できる() {
		Rect rect = RectFactory.create(10, 20, 100, 50, Color.RED);
		assertEquals(10, rect.getX());
		assertEquals(20, rect.getY());
		assertEquals(100, rect.getWidth());
		assertEquals(50, rect.getHeight());
		assertEquals(Color.RED, rect.getColor());
	}

	@Test
	void testContainsPoint() {
		Rect rect = RectFactory.create(10, 10, 100, 100, Color.BLACK);
		assertTrue(rect.contains(new Point(50, 50)));
		assertFalse(rect.contains(new Point(200, 200)));
	}

	@Test
	void testEqualsAndHashCode() {
		Rect r1 = RectFactory.create(0, 0, 50, 50, Color.RED);
		Rect r2 = new Rect(r1.getId(), 0, 0, 50, 50, Color.RED); // ID同じにして等価性チェック
		assertEquals(r1, r2);
		assertEquals(r1.hashCode(), r2.hashCode());
	}

	@Test
	void Idが直前の長方形より1増加すること() {
		Rect r1 = RectFactory.create(0, 0, 10, 10, Color.RED);
		Rect r2 = RectFactory.create(0, 0, 10, 10, Color.RED);
		assertEquals(r1.getId() + 1, r2.getId());
	}
}
