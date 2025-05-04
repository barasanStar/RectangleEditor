package rectEdit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import rectEdit.model.Board;
import rectEdit.model.Rect;

public class BoardPanel extends JPanel {

	private List<Rect> rectangles = List.of();
	private Board board = new Board(500, 400); // 初期サイズ（仮）

	public BoardPanel() {
		setPreferredSize(new Dimension(502, 402));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("Board"));
	}

	/**
	 * モデルから受け取ったデータを更新
	 */
	public void update(List<Rect> rectangles, Board board) {
		this.rectangles = rectangles;
		this.board = board;
		revalidate(); // サイズが変わる可能性がある場合
		repaint(); // 再描画
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (board != null) {
			int cellWidth = getWidth() / board.getWidth();
			int cellHeight = getHeight() / board.getHeight();

			// 長方形を描画
			for (Rect rect : rectangles) {
				g.setColor(rect.getColor());
				g.fillRect(
						rect.getX() * cellWidth,
						rect.getY() * cellHeight,
						rect.getWidth() * cellWidth,
						rect.getHeight() * cellHeight);
				g.setColor(Color.BLACK); // 枠線
				g.drawRect(
						rect.getX() * cellWidth,
						rect.getY() * cellHeight,
						rect.getWidth() * cellWidth,
						rect.getHeight() * cellHeight);
			}
		}
	}
}