package rectEdit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import rectEdit.model.Rect;

public class RectListPanel extends JPanel {
	private final DefaultListModel<Rect> listModel;
	private final JList<Rect> rectList;

	public RectListPanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(245, 245, 245));
		setBorder(BorderFactory.createTitledBorder("Rect List"));

		listModel = new DefaultListModel<>();
		rectList = new JList<>(listModel);
		rectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rectList.setCellRenderer(new RectListCellRenderer());

		add(new JScrollPane(rectList), BorderLayout.CENTER);
	}

	/**
	 * 長方形の一覧を更新（全体差し替え）
	 */
	public void setRectangleList(List<Rect> rectangles) {
		listModel.clear();
		for (Rect rect : rectangles) {
			listModel.addElement(rect);
		}
	}

	/**
	 * IDの集合に基づいて選択状態を反映
	 */
	public void setSelectedIds(Set<Integer> selectedIds) {
		List<Integer> indicesToSelect = new ArrayList<>();
		for (int i = 0; i < listModel.size(); i++) {
			Rect rect = listModel.get(i);
			if (selectedIds.contains(rect.getId())) {
				indicesToSelect.add(i);
			}
		}
		rectList.clearSelection();
		rectList.setSelectedIndices(indicesToSelect.stream().mapToInt(i -> i).toArray());
	}

	/**
	 * 現在選択されている長方形のID集合を返す
	 */
	public Set<Integer> getSelectedIds() {
		return Arrays.stream(rectList.getSelectedIndices())
				.mapToObj(i -> listModel.get(i).getId())
				.collect(Collectors.toSet());
	}

	/**
	 * カスタムセルレンダラ（表示形式の調整）
	 */
	private static class RectListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(
				JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

			JLabel label = (JLabel) super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);

			if (value instanceof Rect rect) {
				label.setText(rect.toString());
			}

			return label;
		}
	}
}
