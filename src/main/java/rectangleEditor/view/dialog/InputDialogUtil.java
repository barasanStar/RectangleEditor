package rectangleEditor.view.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputDialogUtil {
	public static CustomRectInput showCustomRectInputDialog(Component parent) {
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField wField = new JTextField(5);
		JTextField hField = new JTextField(5);
		JButton colorButton = new JButton("色を選択");
		final Color[] selectedColor = { Color.BLACK };

		colorButton.addActionListener(e -> {
			Color c = JColorChooser.showDialog(parent, "色を選択", selectedColor[0]);
			if (c != null)
				selectedColor[0] = c;
		});

		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(new JLabel("X:"));
		panel.add(xField);
		panel.add(new JLabel("Y:"));
		panel.add(yField);
		panel.add(new JLabel("幅:"));
		panel.add(wField);
		panel.add(new JLabel("高さ:"));
		panel.add(hField);
		panel.add(new JLabel("色:"));
		panel.add(colorButton);

		int result = JOptionPane.showConfirmDialog(parent, panel, "長方形の作成", JOptionPane.OK_CANCEL_OPTION);
		if (result != JOptionPane.OK_OPTION)
			return null;

		try {
			int x = Integer.parseInt(xField.getText());
			int y = Integer.parseInt(yField.getText());
			int w = Integer.parseInt(wField.getText());
			int h = Integer.parseInt(hField.getText());
			return new CustomRectInput(x, y, w, h, selectedColor[0]);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(parent, "数値が正しくありません。", "入力エラー", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}