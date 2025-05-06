package rectEdit.handler.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import rectEdit.handler.ActionHandler;
import rectEdit.view.BoardPanel;
import rectEdit.view.RectEditorView;

public class SaveAsImageHandler implements ActionHandler {
	private final BoardPanel boardPanel;
	private final RectEditorView view;
	private final File baseDir;

	public SaveAsImageHandler(BoardPanel boardPanel, RectEditorView view, File baseDir) {
		this.boardPanel = boardPanel;
		this.view = view;
		this.baseDir = baseDir;
	}

	@Override
	public void handle() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(baseDir);
		chooser.setDialogTitle("画像として保存");
		chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
		chooser.setSelectedFile(new File("board.png"));

		if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				BufferedImage image = boardPanel.exportAsImage();
				ImageIO.write(image, "png", file);
				view.appendLog("画像保存完了: " + file.getAbsolutePath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(view, "画像保存に失敗しました", "エラー", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
