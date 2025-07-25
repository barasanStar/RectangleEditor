package rectangleEditor.handler.file;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.model.RectEditorModel;
import rectangleEditor.utils.BoardSaver;
import rectangleEditor.view.RectEditorView;

public class SaveToTextHandler implements ActionHandler {
	private final RectEditorModel model;
	private final RectEditorView view;
	private final File baseDir;

	public SaveToTextHandler(RectEditorModel model, RectEditorView view, File baseDir) {
		this.model = model;
		this.view = view;
		this.baseDir = baseDir;
	}

	@Override
	public void handle() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(baseDir);
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		chooser.setDialogTitle("保存ファイルを選択");

		if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			// 拡張子追加
			if (!file.getName().toLowerCase().endsWith(".txt")) {
				file = new File(file.getAbsolutePath() + ".txt");
			}

			try {
				BoardSaver.saveBoardToFile(model.getBoard(), file);
				view.appendLog("保存完了: " + file.getName());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(view, "保存に失敗しました", "エラー", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
