package rectEdit.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import rectEdit.model.Rect;

public class BoardPanel extends JPanel {

	private List<Rect> rectangles = List.of();

	// 一旦、決め打ちのサイズにしている。何らかの方法でボードサイズを取得すべし。
	public BoardPanel() {
		setPreferredSize(new Dimension(502, 502));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("Board"));
	}

	/**
	 * モデルから受け取ったデータを更新
	 */
	public void update(List<Rect> rectangles) {
		this.rectangles = rectangles;
		//		revalidate(); // サイズが変わる可能性がある場合
		repaint(); // 再描画
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		for (Rect rect : rectangles) {
			// 塗りつぶし
			g2.setColor(rect.getColor());
			g2.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

			//			// 枠線（強調 or 通常）
			//			if (highlightedIds.contains(rect.getId())) {
			//				g2.setColor(Color.RED);
			//				g2.setStroke(new BasicStroke(3));
			//			} else {
			//				g2.setColor(Color.BLACK);
			//				g2.setStroke(new BasicStroke(1));
			//			}

			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
	}
}