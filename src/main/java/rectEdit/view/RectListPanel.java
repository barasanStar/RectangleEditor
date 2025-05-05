package rectEdit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import rectEdit.model.RectEditorModel;

public class RectListPanel extends JPanel {
	private final DefaultListModel<Rect> listModel;
	private final JList<Rect> rectList;
	private RectEditorModel model;

	public RectListPanel(RectEditorModel model) {
		this.model = model;
		setLayout(new BorderLayout());
		setBackground(new Color(245, 245, 245));
		setBorder(BorderFactory.createTitledBorder("Rect List"));

		listModel = new DefaultListModel<>();
		rectList = new JList<>(listModel);
		rectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		rectList.setCellRenderer(new RectListCellRenderer());
		add(new JScrollPane(rectList), BorderLayout.CENTER);

		// 長方形一覧のマウス操作を拾えるように、マウスリスナーを追加
		rectList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = rectList.locationToIndex(e.getPoint());
				if (index != -1) {
					// クリック位置が実際の要素内か確認（空白部分クリックの除外）
					Rect r = listModel.get(index);
					Rectangle cellBounds = rectList.getCellBounds(index, index);
					if (cellBounds != null && cellBounds.contains(e.getPoint())) {
						// 通常の選択（行をクリックした）
						if (r != null && model != null) {
							model.selectOnly(r.getId());
						}
					} else {
						// 空白部分クリック → 選択解除
						rectList.clearSelection();
						if (model != null) {
							model.selectionClear();
						}
					}
				} else {
					// 完全な空白クリック（要素なし）
					rectList.clearSelection();
					if (model != null) {
						model.selectionClear();
					}
				}
			}
		});

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
