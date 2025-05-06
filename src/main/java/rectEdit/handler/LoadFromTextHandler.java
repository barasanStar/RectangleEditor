package rectEdit.handler;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import rectEdit.model.Board;
import rectEdit.model.RectEditorModel;
import rectEdit.utils.BoardLoader;
import rectEdit.view.RectEditorView;

public class LoadFromTextHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;
	private final File baseDir = new File("rect_data");

	public LoadFromTextHandler(RectEditorModel model, RectEditorView view) {
		this.model = model;
		this.view = view;
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}
	}

	@Override
	public void handle() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(baseDir);
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		chooser.setDialogTitle("読み込みファイルを選択");

		if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				Board loaded = BoardLoader.loadBoardFromFile(file);
				model.loadBoard(loaded);
				view.appendLog("読み込み完了: " + file.getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(view, "読み込みに失敗しました", "エラー", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
