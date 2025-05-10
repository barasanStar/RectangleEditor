package rectangleEditor.view.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class InputDialogUtil {

	public static CustomRectInput showCustomRectInputDialog(Component parent, int boardWidth, int boardHeight) {
		int minSize = 1;

		// Spinner（制限付き）
		JSpinner xSpinner = new JSpinner(new SpinnerNumberModel(0, 0, boardWidth - minSize, 1));
		JSpinner ySpinner = new JSpinner(new SpinnerNumberModel(0, 0, boardHeight - minSize, 1));
		JSpinner wSpinner = new JSpinner(new SpinnerNumberModel(50, minSize, boardWidth, 1));
		JSpinner hSpinner = new JSpinner(new SpinnerNumberModel(50, minSize, boardHeight, 1));

		JButton colorButton = new JButton("色を選択");
		final Color[] selectedColor = { Color.BLACK };

		colorButton.addActionListener(e -> {
			Color c = JColorChooser.showDialog(parent, "色を選択", selectedColor[0]);
			if (c != null)
				selectedColor[0] = c;
		});

		JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
		panel.add(new JLabel("X (0–" + (boardWidth - minSize) + "):"));
		panel.add(xSpinner);
		panel.add(new JLabel("Y (0–" + (boardHeight - minSize) + "):"));
		panel.add(ySpinner);
		panel.add(new JLabel("幅 (1–" + boardWidth + "):"));
		panel.add(wSpinner);
		panel.add(new JLabel("高さ (1–" + boardHeight + "):"));
		panel.add(hSpinner);
		panel.add(new JLabel("色:"));
		panel.add(colorButton);

		int result = JOptionPane.showConfirmDialog(parent, panel, "長方形の作成", JOptionPane.OK_CANCEL_OPTION);
		if (result != JOptionPane.OK_OPTION)
			return null;

		int x = (Integer) xSpinner.getValue();
		int y = (Integer) ySpinner.getValue();
		int w = (Integer) wSpinner.getValue();
		int h = (Integer) hSpinner.getValue();

		// 最終チェック：収まるか？
		if (x + w > boardWidth || y + h > boardHeight) {
			JOptionPane.showMessageDialog(parent, "ボードに収まりません。", "入力エラー", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		return new CustomRectInput(x, y, w, h, selectedColor[0]);
	}
}
