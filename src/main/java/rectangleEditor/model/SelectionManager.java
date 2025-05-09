package rectangleEditor.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SelectionManager {

	private Set<Integer> selectedIds = new HashSet<>();

	//--------------------- ここから 状態更新系 --------------------- 
	public boolean selectOnly(int id) {
		if (size() == 1 && contains(id)) {
			return false;
		}
		selectedIds.clear();
		selectedIds.add(id);
		return true;
	}

	public boolean add(int id) {
		if (contains(id)) {
			return false;
		}
		selectedIds.add(id);
		return true;
	}

	public boolean clear() {
		if (!hasSelection()) {
			return false;
		}
		selectedIds.clear();
		return true;
	}

	public boolean remove(int id) {
		if (!contains(id)) {
			return false;
		}
		selectedIds.remove(id);
		return true;
	}

	public boolean toggle(int id) {
		if (selectedIds.contains(id)) {
			selectedIds.remove(id);
		} else {
			selectedIds.add(id);
		}
		return true; // トグルは必ず状態が変わる
	}

	public boolean setSelectedIds(Set<Integer> newIds) {
		if (selectedIds.equals(newIds)) {
			return false; // 変更なし
		}
		selectedIds = new HashSet<>(newIds);
		return true; // 変更あり
	}
	//--------------------- ここまで 状態更新系 --------------------- 

	public Set<Integer> getSelectedIds() {
		return Collections.unmodifiableSet(new HashSet<>(selectedIds));
	}

	public boolean contains(int id) {
		return selectedIds.contains(id);
	}

	public boolean hasSelection() {
		return !selectedIds.isEmpty();
	}

	public int size() {
		return selectedIds.size();
	}
}
