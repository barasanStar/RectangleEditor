package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

	private Board board;

	@BeforeEach
	void setUp() {
		RectFactory.resetIdCounterForTest();
		board = new Board(500, 400); // 有効な初期サイズ
	}

	@Test
	void testValidBoardCreation() {
		assertEquals(500, board.getWidth());
		assertEquals(400, board.getHeight());
		assertEquals(10, board.getCurrentLimit());
	}

	@Test
	void testInvalidBoardSizeThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> new Board(1200, 1000)); // 幅超過
		assertThrows(IllegalArgumentException.class, () -> new Board(500, -10)); // 高さ不正
	}

	@Test
	void testAddRectUpToLimit() {
		for (int i = 0; i < board.getCurrentLimit(); i++) {
			board.addRect(RectFactory.create(i * 10, 0, 10, 10, Color.RED));
		}
		assertTrue(board.isFull());
	}

	@Test
	void testAddRectBeyondLimitFailsLogically() {
		for (int i = 0; i < board.getCurrentLimit(); i++) {
			board.addRect(RectFactory.create(i * 10, 0, 10, 10, Color.RED));
		}
		assertTrue(board.isFull());

		// ロジック的に full なので追加してはいけない（addRect 自体は制限していない点に注意）
		Rect extra = RectFactory.create(0, 0, 10, 10, Color.BLUE);
		board.addRect(extra); // 意図的に制限を無視した場合
		assertEquals(board.getCurrentLimit() + 1, board.getRectanglesReadOnly().size()); // ただし追加される
	}

	@Test
	void testSetRectLimitValidAndInvalid() {
		board.setRectLimit(15);
		assertEquals(15, board.getCurrentLimit());

		assertThrows(IllegalArgumentException.class, () -> board.setRectLimit(0));
		assertThrows(IllegalArgumentException.class, () -> board.setRectLimit(100)); // MAX=20
	}

	@Test
	void testResizeBoardValidAndInvalid() {
		board.setSize(800, 600); // 有効な変更
		assertEquals(800, board.getWidth());
		assertEquals(600, board.getHeight());

		assertThrows(IllegalArgumentException.class, () -> board.setSize(1200, 100)); // 幅超過
	}

	@Test
	void testGetMaxRectCapacity() {
		assertEquals(20, board.getMaxRectCapacity());
	}

	@Test
	void testRemoveRectById() {
		Rect r1 = RectFactory.create(0, 0, 10, 10, Color.RED);
		Rect r2 = RectFactory.create(20, 0, 10, 10, Color.BLUE);
		board.addRect(r1);
		board.addRect(r2);

		assertTrue(board.removeRectById(r1.getId()));
		assertFalse(board.getRectanglesReadOnly().contains(r1));
		assertTrue(board.getRectanglesReadOnly().contains(r2));

		assertFalse(board.removeRectById(999)); // 存在しないID
	}

	@Test
	void testRemoveRect() {
		Rect r1 = RectFactory.create(0, 0, 10, 10, Color.RED);
		Rect r2 = RectFactory.create(20, 0, 10, 10, Color.BLUE);
		board.addRect(r1);
		board.addRect(r2);

		assertTrue(board.removeRect(r1));
		assertFalse(board.getRectanglesReadOnly().contains(r1));
		assertTrue(board.getRectanglesReadOnly().contains(r2));

		assertFalse(board.removeRect(r1)); // 存在しないrect
	}

	@Test
	void testClearAllRects() {
		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		board.addRect(RectFactory.create(10, 10, 20, 20, Color.BLUE));

		board.clearAllRects();
		assertTrue(board.getRectanglesReadOnly().isEmpty());
	}

	@Test
	void 削除されていない長方形データを取り出せる() {
		Rect r0 = RectFactory.create(0, 0, 10, 10, Color.RED);
		Rect r1 = RectFactory.create(0, 0, 20, 30, Color.BLUE);
		board.addRect(r0);
		board.addRect(r1);
		board.removeRectById(0);
		Rect rect0 = board.findById(0);
		assertEquals(null, rect0);
		Rect rect1 = board.findById(1);
		assertEquals(20, rect1.getWidth());
		assertEquals(30, rect1.getHeight());
	}

}
