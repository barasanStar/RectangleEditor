package rectangleEditor.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectangleEditor.model.Rect;
import rectangleEditor.service.ZOrderManager;

public class ZOrderManagerTest {

	private List<Rect> rectList;
	private Rect r0, r1, r2, r3, r4, r5;

	@BeforeEach
	void setup() {
		// テスト用のRectを生成
		r0 = new Rect(0, 0, 0, 10, 10, Color.RED);
		r1 = new Rect(1, 10, 0, 10, 10, Color.BLUE);
		r2 = new Rect(2, 20, 0, 10, 10, Color.GREEN);
		r3 = new Rect(3, 30, 0, 10, 10, Color.BLACK);
		r4 = new Rect(4, 40, 0, 10, 10, Color.CYAN);
		r5 = new Rect(5, 50, 0, 10, 10, Color.MAGENTA);
		rectList = new ArrayList<>(Arrays.asList(r0, r1, r2, r3, r4, r5));
	}

	@Test
	void 長方形一つを最前面へ移動できる_リスト先頭要素を指定() {
		ZOrderManager.bringToFront(rectList, Set.of(0));
		assertEquals(List.of(r1, r2, r3, r4, r5, r0), rectList);
	}

	@Test
	void 長方形一つを最前面へ移動できる_リスト中間要素を指定() {
		ZOrderManager.bringToFront(rectList, Set.of(1));
		assertEquals(List.of(r0, r2, r3, r4, r5, r1), rectList);
	}

	@Test
	void 長方形一つを最前面へ移動できる_リスト末尾要素を指定() {
		ZOrderManager.bringToFront(rectList, Set.of(5));
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形２つを最前面へ移動できるA() {
		ZOrderManager.bringToFront(rectList, Set.of(1, 3));
		assertEquals(List.of(r0, r2, r4, r5, r1, r3), rectList);
	}

	@Test
	void 長方形２つを最前面へ移動できるA_要素指定の順番を変えても結果は同じ() {
		ZOrderManager.bringToFront(rectList, Set.of(3, 1));
		assertEquals(List.of(r0, r2, r4, r5, r1, r3), rectList);
	}

	@Test
	void 長方形２つを最前面へ移動できるB_連続要素() {
		ZOrderManager.bringToFront(rectList, Set.of(2, 1));
		assertEquals(List.of(r0, r3, r4, r5, r1, r2), rectList);
	}

	@Test
	void 長方形２つを最背面へ移動できる() {
		ZOrderManager.sendToBack(rectList, Set.of(2, 0));
		assertEquals(List.of(r0, r2, r1, r3, r4, r5), rectList);
	}

	@Test
	void 長方形３つを最背面へ移動できる() {
		ZOrderManager.sendToBack(rectList, Set.of(0, 1, 3));
		assertEquals(List.of(r0, r1, r3, r2, r4, r5), rectList);
	}

	@Test
	void 長方形１つを前面に移動できる_リスト中間要素を指定() {
		ZOrderManager.moveForward(rectList, Set.of(2));
		assertEquals(List.of(r0, r1, r3, r2, r4, r5), rectList);
	}

	@Test
	void 長方形１つを前面に移動できる_リスト先頭要素を指定() {
		ZOrderManager.moveForward(rectList, Set.of(0));
		assertEquals(List.of(r1, r0, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形１つを前面に移動できる_リスト末尾要素を指定() {
		ZOrderManager.moveForward(rectList, Set.of(5));
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形２つを前面に移動できる_リスト中間の連続要素() {
		ZOrderManager.moveForward(rectList, Set.of(1, 2));
		assertEquals(List.of(r0, r3, r1, r2, r4, r5), rectList);
	}

	@Test
	void 長方形２つを前面に移動できる_リスト先頭の連続要素() {
		ZOrderManager.moveForward(rectList, Set.of(0, 1));
		assertEquals(List.of(r2, r0, r1, r3, r4, r5), rectList);
	}

	@Test
	void 長方形２つを前面に移動できる_リスト末尾の連続要素() {
		ZOrderManager.moveForward(rectList, Set.of(4, 5));
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形２つを前面に移動できる_リスト中間の非連続要素() {
		ZOrderManager.moveForward(rectList, Set.of(1, 4));
		assertEquals(List.of(r0, r2, r1, r3, r5, r4), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる_リスト中間の連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(2, 3));
		assertEquals(List.of(r0, r2, r3, r1, r4, r5), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる_リスト中間の非連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(1, 4));
		assertEquals(List.of(r1, r0, r2, r4, r3, r5), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる_リスト先頭を含む非連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(0, 4));
		assertEquals(List.of(r0, r1, r2, r4, r3, r5), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる_リスト末尾を含む非連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(1, 5));
		assertEquals(List.of(r1, r0, r2, r3, r5, r4), rectList);
	}

	@Test
	void 長方形２つを背面に移動できる_リスト先頭の連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(0, 1));
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形４つを背面に移動できる_連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(0, 1, 2, 3));
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 長方形４つを背面に移動できる_先頭を含まない連続要素() {
		ZOrderManager.moveBackward(rectList, Set.of(1, 2, 3, 4));
		assertEquals(List.of(r1, r2, r3, r4, r0, r5), rectList);
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
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList); // 変更なし
	}

	@Test
	void testMoveForward_nonExistentId() {
		ZOrderManager.moveForward(rectList, Set.of(99)); // 存在しないID
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList); // 変更なし
	}

	@Test
	void bringToFront_引数としてnullが与えられても壊れない() {
		ZOrderManager.bringToFront(rectList, null);
		assertEquals(List.of(r0, r1, r2, r3, r4, r5), rectList);
	}

	@Test
	void 複数操作の連続が順序を壊さない() {
		ZOrderManager.bringToFront(rectList, Set.of(1)); // → r1 を最前面へ
		assertEquals(List.of(r0, r2, r3, r4, r5, r1), rectList);
		ZOrderManager.moveBackward(rectList, Set.of(1)); // → r1 を1つ背面へ
		assertEquals(List.of(r0, r2, r3, r4, r1, r5), rectList);
	}

	@Test
	void 最背面に送ってから前面に一つ移動できる() {
		ZOrderManager.sendToBack(rectList, Set.of(3)); // r3 を最背面へ
		assertEquals(List.of(r3, r0, r1, r2, r4, r5), rectList);
		ZOrderManager.moveForward(rectList, Set.of(3)); // r3 を一つ前面へ
		assertEquals(List.of(r0, r3, r1, r2, r4, r5), rectList);
	}

	@Test
	void 最前面に送ってから背面に一つ移動できる() {
		ZOrderManager.bringToFront(rectList, Set.of(0)); // r0 を最前面へ
		assertEquals(List.of(r1, r2, r3, r4, r5, r0), rectList);
		ZOrderManager.moveBackward(rectList, Set.of(0)); // r0 を一つ背面へ
		assertEquals(List.of(r1, r2, r3, r4, r0, r5), rectList);
	}

	@Test
	void 前面移動してから最背面へ戻せる() {
		ZOrderManager.moveForward(rectList, Set.of(1)); // r1 を一つ前面へ
		assertEquals(List.of(r0, r2, r1, r3, r4, r5), rectList);
		ZOrderManager.sendToBack(rectList, Set.of(1)); // r1 を最背面へ
		assertEquals(List.of(r1, r0, r2, r3, r4, r5), rectList);
	}

	@Test
	void 前後操作を複数回繰り返しても順序が壊れない() {
		ZOrderManager.bringToFront(rectList, Set.of(1, 2)); // r1, r2 を最前面へ
		assertEquals(List.of(r0, r3, r4, r5, r1, r2), rectList);
		ZOrderManager.sendToBack(rectList, Set.of(2)); // r2 を最背面へ
		assertEquals(List.of(r2, r0, r3, r4, r5, r1), rectList);
		ZOrderManager.bringToFront(rectList, Set.of(2)); // 再び r2 を最前面へ
		assertEquals(List.of(r0, r3, r4, r5, r1, r2), rectList);
	}

}
