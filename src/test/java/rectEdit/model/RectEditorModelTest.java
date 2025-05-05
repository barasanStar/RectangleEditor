package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RectEditorModelTest {

	private RectEditorModel model;

	@BeforeEach
	public void setUp() {
		// 幅10×高さ10のボードを持つモデルを初期化
		model = new RectEditorModel(10, 10);
	}

	@Test
	public void testAddRectangle() {
		Rect rect = new Rect(0, 1, 1, 3, 3, Color.RED);
		boolean result = model.addRect(rect);

		assertTrue(result);
		assertEquals(1, model.getRectanglesReadOnly().size());
		assertEquals(rect, model.getRectanglesReadOnly().get(0));
	}

	@Test
	public void testAddRectangleOutOfBounds() {
		Rect rect = new Rect(0, 8, 8, 5, 5, Color.BLUE); // はみ出す
		boolean result = model.addRect(rect);

		assertFalse(result);
		assertTrue(model.getRectanglesReadOnly().isEmpty());
	}

	@Test
	public void testRemoveRectangle() {
		Rect rect = new Rect(0, 2, 2, 3, 3, Color.BLACK);
		model.addRect(rect);
		boolean removed = model.removeRect(rect);

		assertTrue(removed);
		assertTrue(model.getRectanglesReadOnly().isEmpty());
	}

	// このあたりのスナップショットの保存のタイミングや失敗ケアのロジックがあやしい。
	@Test
	public void testUndoRedo() {
		Rect rect = new Rect(0, 0, 0, 2, 2, Color.GREEN);
		model.addRect(rect);
		model.pushSnapshot(); // スナップショット保存

		model.removeRect(rect); // 状態変更
		assertEquals(0, model.getRectanglesReadOnly().size());

		model.undo(); // 元に戻す
		assertEquals(1, model.getRectanglesReadOnly().size());

		model.redo(); // 再実行
		assertEquals(0, model.getRectanglesReadOnly().size());
	}

	@Test
	public void testSetBoardSizeWhenFits() {
		Rect rect = new Rect(0, 1, 1, 3, 3, Color.GRAY);
		model.addRect(rect);

		boolean result = model.setBoardSize(5, 5); // 十分収まる
		assertTrue(result);
		assertEquals(5, model.getBoard().getWidth());
		assertEquals(5, model.getBoard().getHeight());
	}

	@Test
	public void testSetBoardSizeTooSmall() {
		Rect rect = new Rect(0, 1, 1, 6, 6, Color.ORANGE);
		model.addRect(rect);

		boolean result = model.setBoardSize(5, 5); // 収まらない
		assertFalse(result);
		assertEquals(10, model.getBoard().getWidth()); // 元のまま
		assertEquals(10, model.getBoard().getHeight());
	}

	// 必要に応じてさらにテストを追加
}
