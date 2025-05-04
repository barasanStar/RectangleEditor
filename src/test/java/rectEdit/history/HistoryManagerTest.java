package rectEdit.history;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectEdit.model.Board;
import rectEdit.model.Rect;
import rectEdit.model.RectFactory;
import rectEdit.model.SelectionManager;

class HistoryManagerTest {

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
		board.addRect(r0);
		board.addRect(r1);
		selection.select(0);
		history.pushSnapshot();

		// 状態変化：rect2を追加し、選択も変更
		Rect r2 = RectFactory.create(200, 200, 50, 50, Color.GREEN);
		board.addRect(r2);
		selection.selectOnly(2);
		history.pushSnapshot();

		// Undo：状態を巻き戻す
		history.undo();
		history.undo();
		assertEquals(2, board.getRectangles().size()); // r0, r1 のみ
		assertTrue(selection.isSelected(0));
		assertFalse(selection.isSelected(2));

		// Redo：元に戻す
		history.redo();
		assertEquals(3, board.getRectangles().size()); // r0, r1, r2
		assertTrue(selection.isSelected(2));
		assertFalse(selection.isSelected(0));
	}

	@Test
	void testUndoRedoLimits() {
		assertFalse(history.canUndo());
		assertFalse(history.canRedo());

		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		history.pushSnapshot();
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
		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		history.pushSnapshot();

		board.addRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));
		history.pushSnapshot();

		history.undo();
		assertTrue(history.canRedo());

		// 状態変更 → redo は破棄される
		board.addRect(RectFactory.create(40, 40, 10, 10, Color.GREEN));
		history.pushSnapshot();

		assertFalse(history.canRedo());
	}

	@Test
	void 通るように直したtestRestoreExactSelection() {
		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		board.addRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));
		selection.setSelectedIds(Set.of(0));
		history.pushSnapshot();

		selection.setSelectedIds(Set.of(1));
		history.pushSnapshot();

		history.undo();
		assertEquals(Set.of(1), selection.getSelectedIds());

		history.redo();
		assertEquals(Set.of(1), selection.getSelectedIds());
	}

	@Test
	void これは通るtestRestoreExactSelection() {
		board.addRect(RectFactory.create(0, 0, 10, 10, Color.RED));
		board.addRect(RectFactory.create(20, 20, 10, 10, Color.BLUE));

		selection.setSelectedIds(Set.of(0));
		history.pushSnapshot(); // ← ここで保存

		selection.setSelectedIds(Set.of(1));
		// rectanglesに変化がないので pushSnapshot() は呼ばない

		history.undo(); // ← pushSnapshot() された状態に戻る

		// selection={0} に戻っているはず
		assertEquals(Set.of(0), selection.getSelectedIds());
	}

}
