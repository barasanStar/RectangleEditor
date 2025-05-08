package rectEdit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rectEdit.model.Rect;

public class ZOrderManager {

	/**
	 * 【Z順操作】最前面へ
	 * 選択された長方形を、順序を保ったままリスト末尾（最前面）へ移動する。
	 * この操作は引数のリストを破壊的に変更する。
	 */
	public static void bringToFront(List<Rect> rectList, Set<Integer> selectedIds) {
		if (selectedIds == null || selectedIds.isEmpty())
			return;

		log("★[ZOrderManager] before: ", rectList.stream().map(Rect::getId).toList());
		//log("★[ZOrderManager] before: ", rectList.stream().map(r -> r.getId()).toList());
		List<Rect> moving = rectList.stream()
				.filter(r -> selectedIds.contains(r.getId()))
				.collect(Collectors.toCollection(ArrayList::new)); // 可変リスト

		rectList.removeAll(moving);
		rectList.addAll(moving); // 末尾（最前面）に追加
		log("★[ZOrderManager] after : ", rectList.stream().map(r -> r.getId()).toList());
	}

	private static void log(String tag, Object message) {
		System.out.println(
				"[" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
						+ "] " + tag + ": " + message);
	}

	/**
	 * 【Z順操作】最背面へ
	 * 選択された長方形を、順序を保ったままリスト先頭（最背面）へ移動する。
	 * この操作は引数のリストを破壊的に変更する。
	 */
	public static void sendToBack(List<Rect> rectList, Set<Integer> selectedIds) {
		if (selectedIds == null || selectedIds.isEmpty())
			return;

		log("★[ZOrderManager] before sendToBack", rectList.stream().map(Rect::getId).toList());

		List<Rect> moving = rectList.stream()
				.filter(r -> selectedIds.contains(r.getId()))
				.collect(Collectors.toCollection(ArrayList::new));

		rectList.removeAll(moving);
		rectList.addAll(0, moving); // 最背面に追加

		log("★[ZOrderManager] after sendToBack", rectList.stream().map(Rect::getId).toList());
	}

	/**
	 * 【Z順操作】一つ前面へ
	 * 選択された長方形を、順序を保ったまま個別にリストの一つ後ろへ（前面）へ移動する。
	 * この操作は引数のリストを破壊的に変更する。
	 */
	public static void moveForward(List<Rect> rectList, Set<Integer> selectedIds) {
		if (selectedIds == null || selectedIds.isEmpty())
			return;

		log("★[ZOrderManager] before moveForward", rectList.stream().map(Rect::getId).toList());

		// 後ろから前へ処理（Z順の崩壊防止）
		for (int i = rectList.size() - 2; i >= 0; i--) {
			Rect r1 = rectList.get(i);
			Rect r2 = rectList.get(i + 1);
			if (selectedIds.contains(r1.getId()) && !selectedIds.contains(r2.getId())) {
				Collections.swap(rectList, i, i + 1);
			}
		}

		log("★[ZOrderManager] after moveForward", rectList.stream().map(Rect::getId).toList());
	}

	/**
	 * 【Z順操作】一つ背面へ
	 * 選択された長方形を、順序を保ったまま個別にリストの一つ前へ（背面）へ移動する。
	 * この操作は引数のリストを破壊的に変更する。
	 */
	public static void moveBackward(List<Rect> rectList, Set<Integer> selectedIds) {
		if (selectedIds == null || selectedIds.isEmpty())
			return;

		log("★[ZOrderManager] before moveBackward", rectList.stream().map(Rect::getId).toList());

		// 前から後ろへ処理（Z順の崩壊防止）
		for (int i = 1; i < rectList.size(); i++) {
			Rect r1 = rectList.get(i);
			Rect r0 = rectList.get(i - 1);
			if (selectedIds.contains(r1.getId()) && !selectedIds.contains(r0.getId())) {
				Collections.swap(rectList, i, i - 1);
			}
		}

		log("★[ZOrderManager] after moveBackward", rectList.stream().map(Rect::getId).toList());
	}

}
