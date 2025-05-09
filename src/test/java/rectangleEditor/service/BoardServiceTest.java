package rectangleEditor.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectangleEditor.model.Board;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectFactory;
import rectangleEditor.service.BoardService;
import rectangleEditor.service.RectService;

public class BoardServiceTest {

	private Board board;
	private BoardService boardService;

	@BeforeEach
	void setUp() {
		RectFactory.resetIdCounterForTest();
		board = new Board(500, 400); // 初期サイズ
		boardService = new BoardService();
	}

	@Test
	void testCanPlaceInsideBounds() {
		Rect rect = RectFactory.create(100, 100, 50, 50, Color.RED);
		assertTrue(boardService.fitsWithinBoard(board, rect));
	}

	@Test
	void testCannotPlaceOutsideBounds() {
		Rect rect = RectFactory.create(490, 390, 20, 20, Color.RED); // 右下にはみ出る
		assertFalse(boardService.fitsWithinBoard(board, rect));
	}

	@Test
	void testCanPlaceAfterValidMove() {
		Rect original = RectFactory.create(100, 100, 50, 50, Color.BLUE);
		Rect moved = RectService.moveBy(original, 50, 0); // x=150
		assertTrue(boardService.fitsWithinBoard(board, moved));
	}

	@Test
	void testCannotPlaceAfterMoveOutsideBoard() {
		Rect original = RectFactory.create(490, 390, 20, 20, Color.BLUE);
		Rect moved = RectService.moveBy(original, 10, 10); // x=500, y=400 → はみ出す
		assertFalse(boardService.fitsWithinBoard(board, moved));
	}

	@Test
	void testCanPlaceAfterResizeWithinBounds() {
		Rect original = RectFactory.create(100, 100, 50, 50, Color.GREEN);
		Rect resized = RectService.resizeBy(original, 100, 100); // w=150, h=150
		assertTrue(boardService.fitsWithinBoard(board, resized));
	}

	@Test
	void testCannotPlaceAfterResizeOutsideBoard() {
		Rect original = RectFactory.create(450, 350, 50, 50, Color.GREEN);
		Rect resized = RectService.resizeBy(original, 100, 100); // w=150, h=150 → はみ出す
		assertFalse(boardService.fitsWithinBoard(board, resized));
	}

	@Test
	void testCanResizeBoardWhenAllRectsFit() {
		boardService.tryAddRect(board, RectFactory.create(100, 100, 50, 50, Color.BLACK));
		boardService.tryAddRect(board, RectFactory.create(200, 100, 80, 80, Color.BLUE));
		assertTrue(boardService.canResizeBoard(board, 500, 400)); // 現状維持
		assertTrue(boardService.canResizeBoard(board, 300, 200)); // まだ入る
	}

	@Test
	void testCannotResizeBoardWhenSomeRectsOverflow() {
		boardService.tryAddRect(board, RectFactory.create(100, 100, 50, 50, Color.BLACK));
		boardService.tryAddRect(board, RectFactory.create(200, 100, 80, 80, Color.BLUE));
		assertFalse(boardService.canResizeBoard(board, 250, 150)); // 80x80が収まらない
	}

	@Test
	void testRejectRectOutsideBounds() {
		Rect rect = RectFactory.create(490, 390, 20, 20, Color.RED); // はみ出す
		assertThrows(IllegalArgumentException.class, () -> boardService.tryAddRect(board, rect));
	}

	@Test
	void 長方形個数上限を超える数の長方形は格納不可() {
		for (int i = 0; i < board.getSoftLimit(); i++) {
			Rect rect = RectFactory.create(10 * i, 10, 20, 20, Color.BLUE);
			assertTrue(boardService.tryAddRect(board, rect));
		}
		Rect overflow = RectFactory.create(0, 0, 10, 10, Color.BLACK);

		assertThrows(IllegalStateException.class, () -> boardService.tryAddRect(board, overflow));
	}

	@Test
	void 既存の長方形が収まりきらない小さいボードサイズには変更不可() {
		Rect rect = RectFactory.create(100, 100, 300, 300, Color.GREEN);
		boardService.tryAddRect(board, rect);
		assertFalse(boardService.canResizeBoard(board, 200, 200));
	}

	@Test
	void testRemoveRectById() {
		Rect rect = RectFactory.create(0, 0, 10, 10, Color.RED);
		boardService.tryAddRect(board, rect);
		boardService.removeRectById(board, rect.getId());
		assertEquals(0, board.getCurrentRectsCount());
	}

	@Test
	void testClearBoard() {
		boardService.tryAddRect(board, RectFactory.create(0, 0, 10, 10, Color.RED));
		boardService.tryAddRect(board, RectFactory.create(0, 0, 20, 20, Color.BLUE));
		boardService.clearAllRects(board);
		assertEquals(0, board.getCurrentRectsCount());
	}
}
