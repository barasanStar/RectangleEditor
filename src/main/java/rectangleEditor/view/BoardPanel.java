package rectangleEditor.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.JPanel;

import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.model.RectFactory;

public class BoardPanel extends JPanel {
	private List<Rect> rectangles = List.of();
	private Set<Integer> selectedIds = new HashSet<>();
	private RectEditorModel model;
	private Point dragStart = null;
	private Point dragEnd = null;

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

				Optional<Rect> clickedRectOpt = findRectAtPoint(e.getPoint());

				if (clickedRectOpt.isPresent()) {
					Rect clickedRect = clickedRectOpt.get();
					int rectId = clickedRect.getId();
					dragStart = null; // 無効化

					if (e.isControlDown()) {
						// Ctrl押下時：トグル動作
						model.toggleSelection(rectId);
					} else {
						// 通常クリック：単一選択に切り替え
						model.selectOnly(rectId);
					}
				} else {
					if (!e.isControlDown()) {
						model.clearSelection(); // 空白クリック & Ctrlなし：選択解除
					}
					// Ctrl+空白 → 何もしない。
					dragStart = e.getPoint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (dragStart != null) {
					dragEnd = e.getPoint();
					createRectFromDrag();
					dragStart = dragEnd = null;
					repaint();
				}
			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragStart != null) {
					dragEnd = e.getPoint();
					repaint(); // プレビュー表示したい場合に必要
				}
			}
		});
	}

	private void createRectFromDrag() {
		int x1 = dragStart.x;
		int y1 = dragStart.y;
		int x2 = dragEnd.x;
		int y2 = dragEnd.y;

		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x1 - x2);
		int h = Math.abs(y1 - y2);

		if (w == 0 || h == 0)
			return; // 無効なサイズ

		Color color = new Color((int) (Math.random() * 256),
				(int) (Math.random() * 256),
				(int) (Math.random() * 256));

		Rect rect = RectFactory.create(x, y, w, h, color);
		model.pushSnapshot(); // Undo用
		model.tryAddRect(rect); // ボードに収まるか自動チェック
	}

	private static void log(String tag, Object message) {
		System.out.println(
				"[" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
						+ "] " + tag + ": " + message);
	}

	/**
	 * 手元データを最新に更新後、再描画する。
	 */
	public void update(List<Rect> rectangles, Set<Integer> selectedIds) {
		this.rectangles = rectangles;
		this.selectedIds = selectedIds;
		log("★[BoardPanel#update] selectedIds = ", selectedIds);
		repaint(); // 再描画
	}

	public void updateSelectionOnly(Set<Integer> selectedIds) {
		this.selectedIds = selectedIds;
		log("★[BoardPanel#updateSelectionOnly] selectedIds = ", selectedIds);
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

		// プレビュー描画（ドラッグ中のみ）
		if (dragStart != null && dragEnd != null) {
			int x = Math.min(dragStart.x, dragEnd.x);
			int y = Math.min(dragStart.y, dragEnd.y);
			int w = Math.abs(dragEnd.x - dragStart.x);
			int h = Math.abs(dragEnd.y - dragStart.y);

			if (w > 0 && h > 0) {
				g2 = (Graphics2D) g.create();
				g2.setColor(new Color(0, 0, 0, 15)); // 半透明黒
				g2.fillRect(x, y, w, h);

				g2.setColor(Color.BLACK); // 枠線
				g2.drawRect(x, y, w, h);
				g2.dispose();
			}
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