package rectEdit.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import rectEdit.model.Rect;

public class BoardPanel extends JPanel {

	private List<Rect> rectangles = List.of();
	private Set<Integer> selectedIds = new HashSet<>();

	// 【TODO】途中でボードサイズが変わる際は、BoardPanelのサイズも再設定したい。
	//		revalidate(); // サイズが変わる可能性がある場合に呼ぶ
	public BoardPanel(int w, int h) {
		setPreferredSize(new Dimension(w + 2, h + 2));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("Board"));
	}

	/**
	 * 手元データを最新に更新後、再描画する。
	 */
	public void update(List<Rect> rectangles, Set<Integer> selectedIds) {
		this.rectangles = rectangles;
		this.selectedIds = selectedIds;
		repaint(); // 再描画
	}

	public void updateSelectionOnly(Set<Integer> selectedIds) {
		this.selectedIds = selectedIds; // 内部フィールドに代入
		System.out.println("BoardPanel#updateSelectionOnlyです。");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (Rect rect : rectangles) {
			// 塗りつぶし
			g2.setColor(rect.getColor());
			g2.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

			// 枠線
			if (selectedIds.contains(rect.getId())) {
				// 強調
				g2.setColor(Color.RED);
				g2.setStroke(new BasicStroke(3));
			} else {
				// 通常
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(1));
			}

			g2.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
	}
}