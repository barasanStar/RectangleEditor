package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectionManagerTest {

	private SelectionManager selectionManager;

	@BeforeEach
	void setUp() {
		selectionManager = new SelectionManager();
	}

	@Test
	void もともと選択していない値を指定しても例外は発生しない() {
		selectionManager.remove(1);
		assertFalse(selectionManager.contains(1));
	}

	@Test
	void isSelectedおよび取得のテスト() {
		selectionManager.add(1);
		assertTrue(selectionManager.contains(1));
		assertEquals(Set.of(1), selectionManager.getSelectedIds());
	}

	@Test
	void 追加して削除すると空である() {
		selectionManager.add(2);
		selectionManager.remove(2);
		assertFalse(selectionManager.contains(2));
		assertFalse(selectionManager.hasSelection());
	}

	@Test
	void testToggleOn() {
		selectionManager.toggle(3);
		assertTrue(selectionManager.contains(3));
	}

	@Test
	void testToggleOff() {
		selectionManager.add(4);
		selectionManager.toggle(4);
		assertFalse(selectionManager.contains(4));
	}

	@Test
	void testClear() {
		selectionManager.add(1);
		selectionManager.add(2);
		selectionManager.clear();
		assertFalse(selectionManager.hasSelection());
		assertEquals(Set.of(), selectionManager.getSelectedIds());
	}

	@Test
	void testSelectOnly() {
		selectionManager.add(1);
		selectionManager.add(2);
		selectionManager.selectOnly(99);
		assertEquals(Set.of(99), selectionManager.getSelectedIds());
	}

	@Test
	void testSetSelectedIds() {
		selectionManager.setSelectedIds(Set.of(5, 6, 7));
		assertEquals(Set.of(5, 6, 7), selectionManager.getSelectedIds());
	}

	@Test
	void testSize() {
		selectionManager.add(10);
		selectionManager.add(11);
		assertEquals(2, selectionManager.size());
	}

	@Test
	void testUnmodifiableSetReturned() {
		Set<Integer> ids = selectionManager.getSelectedIds();
		assertThrows(UnsupportedOperationException.class, () -> ids.add(100));
	}
}
