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
}
