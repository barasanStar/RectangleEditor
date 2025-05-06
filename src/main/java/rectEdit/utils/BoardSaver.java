package rectEdit.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import rectEdit.model.Board;
import rectEdit.model.Rect;

public class BoardSaver {

	public static void saveBoardToFile(Board board, File file) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
			// ボードサイズ
			out.printf("%d %d%n", board.getWidth(), board.getHeight());

			// Z順を保ったまま
			for (Rect r : board.getRectanglesReadOnly()) {
				Color c = r.getColor();
				out.printf("%d %d %d %d %d %d %d %d%n",
						r.getId(), r.getX(), r.getY(), r.getWidth(), r.getHeight(),
						c.getRed(), c.getGreen(), c.getBlue());
			}
		}
	}
}
