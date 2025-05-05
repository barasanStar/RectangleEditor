package rectEdit.history;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rectEdit.model.Board;
import rectEdit.model.Rect;
import rectEdit.model.SelectionManager;

public class Snapshot {

	private final List<Rect> rectCopies;
	private final Set<Integer> selectedIdCopy;

	private Snapshot(List<Rect> rects, Set<Integer> selectedIds) {
		this.rectCopies = rects;
		this.selectedIdCopy = selectedIds;
	}

	public static Snapshot capture(Board board, SelectionManager selection) {
		// Rect は不変なので浅いコピーでOK
		return new Snapshot(
				new ArrayList<>(board.getRectanglesForMutation()),
				new HashSet<>(selection.getSelectedIds()));
	}

	public void restoreTo(Board board, SelectionManager selection) {
		board.getRectanglesForMutation().clear();
		board.getRectanglesForMutation().addAll(rectCopies);
		selection.setSelectedIds(selectedIdCopy);
	}
}
