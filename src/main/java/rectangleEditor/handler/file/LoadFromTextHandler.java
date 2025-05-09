package rectangleEditor.handler.file;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.Board;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.utils.BoardLoader;
import rectangleEditor.view.RectEditorView;

public class LoadFromTextHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;
	private final File baseDir;

	public LoadFromTextHandler(RectEditorModel model, RectEditorView view, File baseDir) {
		this.model = model;
		this.view = view;
		this.baseDir = baseDir;
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
