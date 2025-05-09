package rectangleEditor.view;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends JPanel {
	private JTextArea textArea;
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public LogPanel() {
		setLayout(new BorderLayout());
		textArea = new JTextArea(9, 30); // 第1引数が高さ
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea);

		add(new JLabel("操作ログ"), BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void appendLog(String message) {
		String time = LocalTime.now().format(TIME_FORMAT);
		textArea.append("[" + time + "] " + message + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength()); // 常に最下部を表示
	}
}
