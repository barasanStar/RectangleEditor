package rectEdit.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	public BoardPanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder("Board"));
	}
}