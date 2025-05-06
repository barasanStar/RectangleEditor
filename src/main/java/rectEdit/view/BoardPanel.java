package rectEdit.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JPanel;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

public class BoardPanel extends JPanel {
	private List<Rect> rectangles = List.of();
	private Set<Integer> selectedIds = new HashSet<>();
	private RectEditorModel model;

	// 【TODO】途中でボードサイズが変わる際は、BoardPanelのサイズも再設定したい。
	//		revalidate(); // サイズが変わる可能性がある場合に呼ぶ
	public BoardPanel(RectEditorModel model) {
		this.model = model;
		int w = model.getBoardWidth();
		int h = model.getBoardHeight();
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.WHITE);

		setupMouseListener();
	}

	// パネル上のマウス操作を拾えるように、マウスリスナーを追加
	private void setupMouseListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point clickedPoint = e.getPoint();
				Optional<Rect> clickedRectOpt = findRectAtPoint(clickedPoint);

				if (clickedRectOpt.isPresent()) {
					Rect clickedRect = clickedRectOpt.get();
					int rectId = clickedRect.getId();

					if (e.isControlDown()) {
						// Ctrl押下時：トグル動作
						model.toggleSelection(rectId);
					} else {
						// 通常クリック：単一選択に切り替え
						model.selectOnly(rectId);
					}
				} else {
					if (!e.isControlDown()) {
						// 空白クリック & Ctrlなし：選択解除
						model.clearSelection();
					}
					// Ctrl+空白 → 何もしない
				}

			}
		});
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
		this.selectedIds = selectedIds;
		repaint(); // 再描画
	}

	private Optional<Rect> findRectAtPoint(Point p) {
		// 最前面からループ
		for (int i = rectangles.size() - 1; i >= 0; i--) {
			Rect rect = rectangles.get(i);
			if (rect.contains(p)) {
				return Optional.of(rect);
			}
		}
		return Optional.empty();
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
				g2.setColor(Color.RED);
				g2.setStroke(new BasicStroke(3)); // 強調
			} else {
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(1)); // 通常
			}
			g2.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
	}

	public BufferedImage exportAsImage() {
		int w = getWidth();
		int h = getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		paintAll(g2); // コンポーネント全体を描画
		g2.dispose();
		return image;
	}
}