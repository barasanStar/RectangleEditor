package rectEdit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectFactory;
import rectEdit.service.ZOrderManager;

public class ZOrderMultiSelectionTest {

	RectEditorModel model;

	@BeforeEach
	void setup() {
		model = new RectEditorModel();
		for (int i = 0; i < 6; i++) {
			Rect rect = RectFactory.create(i * 10 + 10, i * 10 + 20, 60, 40,
					new Color(30 * i, 30 * i, 30 * i));
			model.tryAddRect(rect);
		}
		assertEquals(6, model.getCurrentRectsCount());
	}

	@Test
	void testMultiSelectionZOrderChange() {
		// 1. 複数選択（id1, id3, id4）
		Set<Integer> selectedIds = Set.of(1, 3, 4);
		model.getSelectionManager().setSelectedIds(selectedIds);

		// 2. 一つ背面へ
		ZOrderManager.moveBackward(model.getRectanglesForMutation(), selectedIds);

		// 3. 一つ背面へ
		ZOrderManager.moveBackward(model.getRectanglesForMutation(), selectedIds);

		// 4. 最前面へ
		ZOrderManager.bringToFront(model.getRectanglesForMutation(), selectedIds);

		// Z順を確認
		List<Integer> actualZOrder = model.getRectanglesReadOnly().stream()
				.map(Rect::getId)
				.collect(Collectors.toList());

		System.out.println("Z順: " + actualZOrder);

		// 5. 選択解除
		model.clearSelection();
		assertTrue(model.getSelectionManager().getSelectedIds().isEmpty());

		// 6. 非選択状態でのZ順操作は何も起こらないこと（念のため）
		List<Integer> before = model.getRectanglesReadOnly().stream()
				.map(Rect::getId)
				.collect(Collectors.toList());

		// 一つ前面へ（無視されるべき）
		ZOrderManager.moveForward(model.getRectanglesForMutation(), selectedIds);

		List<Integer> after = model.getRectanglesReadOnly().stream()
				.map(Rect::getId)
				.collect(Collectors.toList());

		assertEquals(before, after, "非選択状態ではZ順に変化なし");
	}
}
