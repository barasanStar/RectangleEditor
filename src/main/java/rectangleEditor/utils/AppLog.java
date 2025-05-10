package rectangleEditor.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppLog {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	private static boolean flagPrintToConsole = true;

	public static void log(String tag, Object message) {
		String timestamp = LocalTime.now().format(FORMATTER);
		String fullMessage = "[" + timestamp + "] " + tag + ": " + message;

		if (flagPrintToConsole) {
			System.out.println(fullMessage);
		}
	}

}
