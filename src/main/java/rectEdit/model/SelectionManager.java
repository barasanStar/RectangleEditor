package rectEdit.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SelectionManager {

	private final Set<Integer> selectedIds = new HashSet<>();

	public void selectOnly(int id) {
		selectedIds.clear();
		selectedIds.add(id);
	}

	public void add(int id) {
		selectedIds.add(id);
	}

	public void clear() {
		selectedIds.clear();
	}

	public void remove(int id) {
		selectedIds.remove(id);
	}

	public void toggle(int id) {
		if (selectedIds.contains(id)) {
			selectedIds.remove(id);
		} else {
			selectedIds.add(id);
		}
	}

	public void setSelectedIds(Set<Integer> ids) {
		selectedIds.clear();
		selectedIds.addAll(ids);
	}

	public Set<Integer> getSelectedIds() {
		return Collections.unmodifiableSet(new HashSet<>(selectedIds));
	}

	public boolean isSelected(int id) {
		return selectedIds.contains(id);
	}

	public boolean isEmpty() {
		return selectedIds.isEmpty();
	}

	public int size() {
		return selectedIds.size();
	}

}
