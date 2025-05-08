package rectEdit.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;

public class ZOrderManagerTest {

	private List<Rect> rectList;
	private Rect r0, r1, r2, r3;

	@BeforeEach
	void setup() {
		// テスト用のRectを生成
		r0 = new Rect(0, 0, 0, 10, 10, Color.RED);
		r1 = new Rect(1, 10, 0, 10, 10, Color.BLUE);
		r2 = new Rect(2, 20, 0, 10, 10, Color.GREEN);
		r3 = new Rect(3, 30, 0, 10, 10, Color.BLACK);

		rectList = new ArrayList<>(Arrays.asList(r0, r1, r2, r3));
	}

	@Test
	void 長方形一つを最前面へ移動できるA() {
		ZOrderManager.bringToFront(rectList, Set.of(0));
		assertEquals(List.of(r1, r2, r3, r0), rectList);
	}

	@Test
	void 長方形一つを最前面へ移動できるB() {
		ZOrderManager.bringToFront(rectList, Set.of(1));
		assertEquals(List.of(r0, r2, r3, r1), rectList);
	}

	@Test
	void 長方形一つを最前面へ移動できるC() {
		ZOrderManager.bringToFront(rectList, Set.of(3));
		assertEquals(List.of(r0, r1, r2, r3), rectList);
	}

	@Test
	void 長方形２つを最前面へ移動できるA() {
		ZOrderManager.bringToFront(rectList, Set.of(1, 3));
		assertEquals(List.of(r0, r2, r1, r3), rectList);
	}

	@Test
	void 長方形２つを最前面へ移動できるB() {
		ZOrderManager.bringToFront(rectList, Set.of(2, 1));
		assertEquals(List.of(r0, r3, r1, r2), rectList);
	}

	@Test
	void 長方形２つを最背面へ移動できる() {
		ZOrderManager.sendToBack(rectList, Set.of(2, 0));
		assertEquals(List.of(r0, r2, r1, r3), rectList);
	}

	@Test
	void 長方形２つを最背面へ移動できる_指定する順番の影響を受けない() {
		ZOrderManager.sendToBack(rectList, Set.of(0, 2));
		assertEquals(List.of(r0, r2, r1, r3), rectList);
	}

	@Test
	void 長方形３つを最背面へ移動できる() {
		ZOrderManager.sendToBack(rectList, Set.of(0, 1, 3));
		assertEquals(List.of(r0, r1, r3, r2), rectList);
	}

	@Test
	void 長方形１つを前面に移動できる() {
		ZOrderManager.moveForward(rectList, Set.of(2));
		assertEquals(List.of(r0, r1, r3, r2), rectList);
	}

	@Test
	void 長方形２つを前面に移動できる() {
		ZOrderManager.moveForward(rectList, Set.of(1, 2));
		assertEquals(List.of(r0, r3, r1, r2), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる() {
		ZOrderManager.moveBackward(rectList, Set.of(2, 3));
		assertEquals(List.of(r0, r2, r3, r1), rectList);
	}

	@Test
	void 長方形４つを背面に移動できる() {
		ZOrderManager.moveBackward(rectList, Set.of(0, 1, 2, 3));
		assertEquals(List.of(r0, r1, r2, r3), rectList);
	}

	@Test
	void testBringToFront_emptyList() {
		List<Rect> emptyList = new ArrayList<>();
		ZOrderManager.bringToFront(emptyList, Set.of(1, 2));
		assertTrue(emptyList.isEmpty());
	}

	@Test
	void testSendToBack_emptySelection() {
		ZOrderManager.sendToBack(rectList, Set.of()); // 選択なし
		assertEquals(List.of(r0, r1, r2, r3), rectList); // 変更なし
	}

	@Test
	void testMoveForward_nonExistentId() {
		ZOrderManager.moveForward(rectList, Set.of(99)); // 存在しないID
		assertEquals(List.of(r0, r1, r2, r3), rectList); // 変更なし
	}

	@Test
	void testBringToFront_alreadyAtFront() {
		// r2, r3 はすでに末尾側
		ZOrderManager.bringToFront(rectList, Set.of(2, 3));
		assertEquals(List.of(r0, r1, r2, r3), rectList); // 順序変わらず
	}

	@Test
	void testSendToBack_alreadyAtBack() {
		// r0, r1 はすでに先頭側
		ZOrderManager.sendToBack(rectList, Set.of(0, 1));
		assertEquals(List.of(r0, r1, r2, r3), rectList); // 順序変わらず
	}

	@Test
	void testOrderPreservedOnBringToFront() {
		// r1, r2 を前面へ
		ZOrderManager.bringToFront(rectList, Set.of(2, 1));
		assertEquals(List.of(r0, r3, r1, r2), rectList);
	}

	@Test
	void moveForward_順序保持を確認する() {
		// 選択された r1, r2 は順序保持されて末尾へ
		ZOrderManager.moveForward(rectList, Set.of(1, 2));
		assertEquals(List.of(r0, r3, r1, r2), rectList);
		//※これは既存 長方形２つを前面に移動できる() でも間接的に確認されていますが、
		//※意図を明示したケースとして用意しました。
	}

	@Test
	void moveForward_離れた選択要素を処理できる() {
		//「選択された2つが離れていて、途中に非選択がある」ケース：
		// r0, r2 を選択して前面へ
		ZOrderManager.moveForward(rectList, Set.of(0, 2));
		assertEquals(List.of(r1, r0, r3, r2), rectList);
	}

	@Test
	void moveBackward_離れた選択要素を処理できる() {
		// r1, r3 を選択して背面へ
		ZOrderManager.moveBackward(rectList, Set.of(1, 3));
		assertEquals(List.of(r1, r0, r3, r2), rectList);
	}

	@Test
	void bringToFront_null選択を無視する() {
		ZOrderManager.bringToFront(rectList, null);
		assertEquals(List.of(r0, r1, r2, r3), rectList);
	}

	@Test
	void 複数操作の連続が順序を壊さない() {
		ZOrderManager.bringToFront(rectList, Set.of(1)); // → r1 を末尾に
		ZOrderManager.moveBackward(rectList, Set.of(1)); // → r1 を1つ戻す
		assertEquals(List.of(r0, r2, r1, r3), rectList);
	}

	@Test
	void moveForward_非連続な複数選択を正しく処理できる() {
		// 6個の長方形をセットアップ
		Rect r0 = new Rect(0, 0, 0, 10, 10, Color.RED);
		Rect r1 = new Rect(1, 10, 0, 10, 10, Color.BLUE); // ← 選択
		Rect r2 = new Rect(2, 20, 0, 10, 10, Color.GREEN);
		Rect r3 = new Rect(3, 30, 0, 10, 10, Color.YELLOW);
		Rect r4 = new Rect(4, 40, 0, 10, 10, Color.CYAN); // ← 選択
		Rect r5 = new Rect(5, 50, 0, 10, 10, Color.MAGENTA);

		List<Rect> list = new ArrayList<>(List.of(r0, r1, r2, r3, r4, r5));
		ZOrderManager.moveForward(list, Set.of(1, 4));

		// 期待される順序: r1とr4がそれぞれ一つ前進
		List<Rect> expected = List.of(r0, r2, r1, r3, r5, r4);
		assertEquals(expected, list);
	}

}
