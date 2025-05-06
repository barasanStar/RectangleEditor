package rectEdit.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rectEdit.model.Board;
import rectEdit.model.Rect;

public class BoardLoader {

	public static Board loadBoardFromFile(File file) throws IOException {
		int nextId = 0;
		try (Scanner scanner = new Scanner(file)) {
			// コメント行をスキップ
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.startsWith("#")) {
					// 最初の数値行（width height）
					Scanner lineScanner = new Scanner(line);
					int width = lineScanner.nextInt();
					int height = lineScanner.nextInt();

					List<Rect> rects = new ArrayList<>();

					// 続きの行（rect定義）
					while (scanner.hasNextLine()) {
						String rectLine = scanner.nextLine().trim();
						if (rectLine.isEmpty() || rectLine.startsWith("#"))
							continue;

						Scanner rectScanner = new Scanner(rectLine);
						int x = rectScanner.nextInt();
						int y = rectScanner.nextInt();
						int w = rectScanner.nextInt();
						int h = rectScanner.nextInt();
						int r = rectScanner.nextInt();
						int g = rectScanner.nextInt();
						int b = rectScanner.nextInt();

						Color color = new Color(r, g, b);
						rects.add(new Rect(nextId++, x, y, w, h, color));
					}

					return new Board(width, height, rects);
				}
			}

			throw new IOException("ファイル形式が正しくありません");
		}
	}
}
