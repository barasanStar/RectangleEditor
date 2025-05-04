package rectEdit.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectionManagerTest {

	private SelectionManager selection;

	@BeforeEach
	void setUp() {
		selection = new SelectionManager();
	}

	@Test
	void もともと選択していない値を指定しても例外は発生しない() {
		selection.deselect(1);
		assertFalse(selection.isSelected(1));
	}

	@Test
	void testSelectAndIsSelected() {
		selection.select(1);
		assertTrue(selection.isSelected(1));
		assertEquals(Set.of(1), selection.getSelectedIds());
	}

	@Test
	void testDeselect() {
		selection.select(2);
		selection.deselect(2);
		assertFalse(selection.isSelected(2));
		assertTrue(selection.isEmpty());
	}

	@Test
	void testToggleOn() {
		selection.toggle(3);
		assertTrue(selection.isSelected(3));
	}

	@Test
	void testToggleOff() {
		selection.select(4);
		selection.toggle(4);
		assertFalse(selection.isSelected(4));
	}

	@Test
	void testClear() {
		selection.select(1);
		selection.select(2);
		selection.clear();
		assertTrue(selection.isEmpty());
		assertEquals(Set.of(), selection.getSelectedIds());
	}

	@Test
	void testSelectOnly() {
		selection.select(1);
		selection.select(2);
		selection.selectOnly(99);
		assertEquals(Set.of(99), selection.getSelectedIds());
	}

	@Test
	void testSetSelectedIds() {
		selection.setSelectedIds(Set.of(5, 6, 7));
		assertEquals(Set.of(5, 6, 7), selection.getSelectedIds());
	}

	@Test
	void testSize() {
		selection.select(10);
		selection.select(11);
		assertEquals(2, selection.size());
	}

	@Test
	void testUnmodifiableSetReturned() {
		Set<Integer> ids = selection.getSelectedIds();
		assertThrows(UnsupportedOperationException.class, () -> ids.add(100));
	}
}
