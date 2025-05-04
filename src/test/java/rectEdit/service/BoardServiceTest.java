package rectEdit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectEdit.model.Board;
import rectEdit.model.Rect;
import rectEdit.model.RectFactory;

public class BoardServiceTest {

	private Board board;

	@BeforeEach
	void setUp() {
		RectFactory.resetIdCounterForTest();
		board = new Board(500, 400); // 初期サイズ
	}

	@Test
	void testCanPlaceInsideBounds() {
		Rect rect = RectFactory.create(100, 100, 50, 50, Color.RED);
		assertTrue(BoardService.canPlace(board, rect));
	}

	@Test
	void testCannotPlaceOutsideBounds() {
		Rect rect = RectFactory.create(490, 390, 20, 20, Color.RED); // 右下にはみ出る
		assertFalse(BoardService.canPlace(board, rect));
	}

	@Test
	void testCanPlaceAfterValidMove() {
		Rect original = RectFactory.create(100, 100, 50, 50, Color.BLUE);
		Rect moved = RectService.moveBy(original, 50, 0); // x=150
		assertTrue(BoardService.canPlace(board, moved));
	}

	@Test
	void testCannotPlaceAfterMoveOutsideBoard() {
		Rect original = RectFactory.create(490, 390, 20, 20, Color.BLUE);
		Rect moved = RectService.moveBy(original, 10, 10); // x=500, y=400 → はみ出す
		assertFalse(BoardService.canPlace(board, moved));
	}

	@Test
	void testCanPlaceAfterResizeWithinBounds() {
		Rect original = RectFactory.create(100, 100, 50, 50, Color.GREEN);
		Rect resized = RectService.resizeBy(original, 100, 100); // w=150, h=150
		assertTrue(BoardService.canPlace(board, resized));
	}

	@Test
	void testCannotPlaceAfterResizeOutsideBoard() {
		Rect original = RectFactory.create(450, 350, 50, 50, Color.GREEN);
		Rect resized = RectService.resizeBy(original, 100, 100); // w=150, h=150 → はみ出す
		assertFalse(BoardService.canPlace(board, resized));
	}

	@Test
	void testCanResizeBoardWhenAllRectsFit() {
		board.addRect(RectFactory.create(100, 100, 50, 50, Color.BLACK));
		board.addRect(RectFactory.create(200, 100, 80, 80, Color.BLUE));
		assertTrue(BoardService.canResizeBoard(board, 500, 400)); // 現状維持
		assertTrue(BoardService.canResizeBoard(board, 300, 200)); // まだ入る
	}

	@Test
	void testCannotResizeBoardWhenSomeRectsOverflow() {
		board.addRect(RectFactory.create(100, 100, 50, 50, Color.BLACK));
		board.addRect(RectFactory.create(200, 100, 80, 80, Color.BLUE));
		assertFalse(BoardService.canResizeBoard(board, 250, 150)); // 80x80が収まらない
	}

	@Test
	void testRejectRectOutsideBounds() {
		Rect rect = RectFactory.create(490, 390, 20, 20, Color.RED); // はみ出す
		assertFalse(BoardService.canAdd(board, rect));
	}

	@Test
	void 長方形個数上限を超える数の長方形は格納不可() {
		for (int i = 0; i < 10; i++) {
			Rect rect = RectFactory.create(10 * i, 10, 20, 20, Color.BLUE);
			assertTrue(BoardService.addRectIfValid(board, rect));
		}
		Rect overflow = RectFactory.create(0, 0, 10, 10, Color.BLACK);
		assertFalse(BoardService.canAdd(board, overflow));
	}

	@Test
	void 既存の長方形が収まりきらない小さいボードサイズには変更不可() {
		Rect rect = RectFactory.create(100, 100, 300, 300, Color.GREEN);
		board.addRect(rect);
		assertFalse(BoardService.canResizeBoard(board, 200, 200));
	}

	@Test
	void testRemoveRectById() {
		Rect rect = RectFactory.create(0, 0, 10, 10, Color.RED);
		board.addRect(rect);
		BoardService.removeRect(board, rect.getId());
		assertTrue(board.getRectangles().isEmpty());
	}

	@Test
	void testClearBoard() {
		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		board.addRect(RectFactory.create(0, 0, 20, 20, Color.BLUE));
		BoardService.clearBoard(board);
		assertEquals(0, board.getRectangles().size());
	}
}
