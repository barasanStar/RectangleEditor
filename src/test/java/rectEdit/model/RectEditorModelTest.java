package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RectEditorModelTest {

	private RectEditorModel model;

	@BeforeEach
	public void setUp() {
		// 幅500×高さ500のボードを持つモデルを初期化
		model = new RectEditorModel();
	}

	@Test
	public void testAddRectangle() {
		Rect rect = new Rect(0, 1, 1, 3, 3, Color.RED);
		boolean result = model.tryAddRect(rect);

		assertTrue(result);
		assertEquals(1, model.getRectanglesReadOnly().size());
		assertEquals(rect, model.getRectanglesReadOnly().get(0));
	}

	@Test
	public void testAddRectangleOutOfBounds() {
		Rect rect = new Rect(0, 8, 8, 500, 5, Color.BLUE); // はみ出す
		boolean result = model.tryAddRect(rect);

		assertFalse(result);
		assertTrue(model.getRectanglesReadOnly().isEmpty());
	}

	@Test
	public void testRemoveRectangle() {
		Rect rect = new Rect(0, 2, 2, 3, 3, Color.BLACK);
		model.tryAddRect(rect);
		boolean removed = model.removeRect(rect);

		assertTrue(removed);
		assertTrue(model.getRectanglesReadOnly().isEmpty());
	}

	@Test
	public void testUndoRedo() {
		Rect rect = new Rect(0, 0, 0, 2, 2, Color.GREEN);
		model.pushSnapshot(); // スナップショット保存
		model.tryAddRect(rect);

		model.pushSnapshot(); // スナップショット保存
		model.removeRect(rect); // 状態変更
		assertEquals(0, model.getCurrentRectsCount());

		model.undo(); // 元に戻す
		assertEquals(1, model.getCurrentRectsCount());

		model.redo(); // 再実行
		assertEquals(0, model.getCurrentRectsCount());
	}

	@Test
	public void testSetBoardSizeWhenFits() {
		Rect rect = new Rect(0, 1, 1, 3, 3, Color.GRAY);
		model.tryAddRect(rect);

		boolean result = model.setBoardSize(5, 5); // 十分収まる
		assertTrue(result);
		assertEquals(5, model.getBoard().getWidth());
		assertEquals(5, model.getBoard().getHeight());
	}

	@Test
	public void testSetBoardSizeTooSmall() {
		Rect rect = new Rect(0, 1, 1, 6, 6, Color.ORANGE);
		model.tryAddRect(rect);

		boolean result = model.setBoardSize(5, 5); // 収まらない
		assertFalse(result);
		assertEquals(500, model.getBoard().getWidth()); // 元のまま
		assertEquals(500, model.getBoard().getHeight());
	}

	// 必要に応じてさらにテストを追加
}
