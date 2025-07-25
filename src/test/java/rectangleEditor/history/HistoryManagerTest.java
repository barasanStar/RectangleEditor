package rectangleEditor.history;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectangleEditor.history.HistoryManager;
import rectangleEditor.model.Board;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectFactory;
import rectangleEditor.model.SelectionManager;

public class HistoryManagerTest {

	private Board board;
	private SelectionManager selection;
	private HistoryManager history;

	@BeforeEach
	void setUp() {
		RectFactory.resetIdCounterForTest(); // IDリセット
		board = new Board(500, 500);
		selection = new SelectionManager();
		history = new HistoryManager(board, selection);
	}

	@Test
	void testPushAndUndoRedo() {
		// 初期状態：rect0, rect1を追加
		Rect r0 = RectFactory.create(0, 0, 50, 50, Color.RED);
		Rect r1 = RectFactory.create(100, 100, 50, 50, Color.BLUE);
		history.pushSnapshot();
		board.tryAddRect(r0);

		history.pushSnapshot();
		board.tryAddRect(r1);
		selection.add(0);

		// 状態変化：rect2を追加し、選択も変更
		Rect r2 = RectFactory.create(200, 200, 50, 50, Color.GREEN);
		history.pushSnapshot();
		board.tryAddRect(r2);
		selection.selectOnly(2);

		// Undo：状態を巻き戻す
		history.undo();
		assertEquals(2, board.getCurrentRectsCount()); // r0, r1 のみ
		assertTrue(selection.contains(0));
		assertFalse(selection.contains(2));

		// Redo：元に戻す
		history.redo();
		assertEquals(3, board.getCurrentRectsCount()); // r0, r1, r2
		assertTrue(selection.contains(2));
		assertFalse(selection.contains(0));
	}

	@Test
	void testUndoRedoLimits() {
		assertFalse(history.canUndo());
		assertFalse(history.canRedo());

		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		assertTrue(history.canUndo());

		history.undo();
		assertTrue(history.canRedo());
		assertFalse(history.canUndo());

		history.redo();
		assertTrue(history.canUndo());
		assertFalse(history.canRedo());
	}

	@Test
	void testPushClearsRedoStack() {
		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(0, 0, 10, 10, Color.RED));

		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));

		history.undo();
		assertTrue(history.canRedo());

		// 状態変更 → redo は破棄される
		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(40, 40, 10, 10, Color.GREEN));

		assertFalse(history.canRedo());
	}

	@Test
	void 通るように直したtestRestoreExactSelection() {
		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(0, 0, 10, 10, Color.RED));

		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));
		selection.setSelectedIds(Set.of(0));
		selection.setSelectedIds(Set.of(1));

		history.undo();
		assertEquals(Set.of(), selection.getSelectedIds());

		history.redo();
		assertEquals(Set.of(1), selection.getSelectedIds());
	}

	@Test
	void これは通るtestRestoreExactSelection() {
		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		assertEquals(Set.of(), selection.getSelectedIds());
		selection.setSelectedIds(Set.of(0));
		assertEquals(Set.of(0), selection.getSelectedIds());

		history.pushSnapshot();
		board.tryAddRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));
		selection.setSelectedIds(Set.of(1));

		history.undo(); // ← pushSnapshot() された状態に戻る

		// selection={0} に戻っているはず
		assertEquals(Set.of(0), selection.getSelectedIds());
	}
}
