package rectEdit.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import rectEdit.model.Rect;

public class ZOrderManager {

	// 最前面へ：選択された長方形を、現在順を保ったまま末尾へ
	public static void bringToFront(List<Rect> rectList, Set<Integer> selectedIds) {
		List<Rect> moving = rectList.stream()
				.filter(r -> selectedIds.contains(r.getId()))
				.toList();
		rectList.removeAll(moving);
		rectList.addAll(moving); // 末尾（最前面）に追加
	}

	// 最背面へ：選択された長方形を、現在順を保ったまま先頭へ
	public static void sendToBack(List<Rect> rectList, Set<Integer> selectedIds) {
		List<Rect> moving = rectList.stream()
				.filter(r -> selectedIds.contains(r.getId()))
				.toList();
		rectList.removeAll(moving);
		rectList.addAll(0, moving); // 先頭（最背面）に追加
	}

	// 一つ前面へ：選択された長方形だけを個別に前面へ（後ろからスワップ）
	public static void moveForward(List<Rect> rectList, Set<Integer> selectedIds) {
		for (int i = rectList.size() - 2; i >= 0; i--) {
			Rect r1 = rectList.get(i);
			Rect r2 = rectList.get(i + 1);
			if (selectedIds.contains(r1.getId()) && !selectedIds.contains(r2.getId())) {
				Collections.swap(rectList, i, i + 1);
			}
		}
	}

	// 一つ背面へ：選択された長方形だけを個別に背面へ（前からスワップ）
	public static void moveBackward(List<Rect> rectList, Set<Integer> selectedIds) {
		for (int i = 1; i < rectList.size(); i++) {
			Rect r1 = rectList.get(i);
			Rect r0 = rectList.get(i - 1);
			if (selectedIds.contains(r1.getId()) && !selectedIds.contains(r0.getId())) {
				Collections.swap(rectList, i, i - 1);
			}
		}
	}
}
