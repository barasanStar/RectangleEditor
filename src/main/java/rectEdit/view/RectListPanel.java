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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.model.RectEditorModelListener;

public class RectListPanel extends JPanel implements RectEditorModelListener {
	private final DefaultListModel<Rect> listModel;
	private final JList<Rect> rectList;
	private RectEditorModel model;
	private boolean updatingFromModel = false;

	public RectListPanel(RectEditorModel model) {
		this.model = model;
		setLayout(new BorderLayout());
		setBackground(new Color(245, 245, 245));
		setBorder(BorderFactory.createTitledBorder("Rect List"));

		listModel = new DefaultListModel<>();
		rectList = new JList<>(listModel);
		rectList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		rectList.addListSelectionListener(e -> {
			if (updatingFromModel) {
				//				System.out.println("[循環防止] ListSelectionListener 抑止");
				log("★[循環防止] ListSelectionListener 抑止", getSelectedIds());

				return; // 循環を防止				
			}

			if (!e.getValueIsAdjusting()) {
				updatingFromModel = true; // ← 追加（保護用）
				try {
					int[] selected = rectList.getSelectedIndices();
					Set<Integer> selectedSet = Arrays.stream(selected).boxed().collect(Collectors.toSet());
					model.setSelectedIds(selectedSet);
				} finally {
					updatingFromModel = false; // ← 追加（保護用）
				}
			}
		});

		// カスタムセルレンダラー：モデルの選択状態に応じた強調表示
		rectList.setCellRenderer(new RectListCellRenderer());

		add(new JScrollPane(rectList), BorderLayout.CENTER);

		rectList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = rectList.locationToIndex(e.getPoint());
				Rectangle bounds = rectList.getCellBounds(index, index);

				if (bounds == null || !bounds.contains(e.getPoint())) {
					// 空白部分がクリックされたとみなす
					rectList.clearSelection();
					//					model.clearSelection(); // モデル側も選択解除

					// ✅ 後回しにすることでイベントの衝突を避ける
					SwingUtilities.invokeLater(() -> {
						model.clearSelection(); // モデル側も選択解除
					});
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

	private static void log(String tag, Object message) {
		System.out.println(
				"[" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
						+ "] " + tag + ": " + message);
	}

	/**
	 * IDの集合に基づいて選択状態を反映
	 */
	public void setSelectedIds(Set<Integer> selectedIds) {
		updatingFromModel = true; // 対策1として追加
		try {
			log("★[RectListPanel#setSelectedIds] selectedIds = ", selectedIds);
			List<Integer> indicesToSelect = new ArrayList<>();
			for (int i = 0; i < listModel.size(); i++) {
				Rect rect = listModel.get(i);
				if (selectedIds.contains(rect.getId())) {
					indicesToSelect.add(i);
				}
			}
			rectList.clearSelection();
			rectList.setSelectedIndices(indicesToSelect.stream().mapToInt(i -> i).toArray());
			log("★[RectListPanel] selectedIndices = ", indicesToSelect);

		} finally {
			updatingFromModel = true; // 対策1として追加
		}
	}

	public void updateListAndSelection(List<Rect> rects, Set<Integer> selectedIds) {
		updatingFromModel = true; // 対策2として追加
		try {
			listModel.clear();
			for (Rect r : rects) {
				listModel.addElement(r);
			}
			setSelectedIds(selectedIds); // ID→index変換して選択復元

		} finally {
			updatingFromModel = false; // 対策2として追加
		}
	}

	/**
	 * 現在選択されている長方形のID集合を返す
	 */
	public Set<Integer> getSelectedIds() {
		return Arrays.stream(rectList.getSelectedIndices())
				.mapToObj(i -> listModel.get(i).getId())
				.collect(Collectors.toSet());
	}

	// 非staticにすることで model にアクセス可能
	private class RectListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);

			if (value instanceof Rect rect) {
				//				label.setText(rect.toString()); // ここを変えたい

				// Z順は index（リストの表示順）として取得できる
				label.setText(String.format("[ID:%d, Z:%d] %s", rect.getId(), index, rect.toString()));

				Set<Integer> selectedIds = model.getSelectionManager().getSelectedIds();
				if (selectedIds.contains(rect.getId())) {
					// モデル側で選択されていれば強調表示
					label.setBackground(list.getSelectionBackground());
					label.setForeground(list.getSelectionForeground());
					label.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
					label.setOpaque(true);
				} else {
					label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
					label.setOpaque(true);
				}
			}

			return label;
		}
	}

	@Override
	public void onRectsChanged(String operationLogMessage) {
	}

	@Override
	public void onSelectionChanged(String operationLogMessage) {
		Set<Integer> selectedIds = model.getSelectionManager().getSelectedIds();

		updatingFromModel = true;
		try {
			setSelectedIds(selectedIds); // JList の選択状態を反映
		} finally {
			updatingFromModel = false;
		}

		rectList.repaint(); // レンダラー強制再描画（重要！）
	}
}
