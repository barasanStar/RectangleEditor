package rectangleEditor.constants;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class Constants {
	public static final String BASE_DIRECTORY_NAME = "rect_data";
	public static final String ERROR_FILE_NOT_FOUND = "ファイルが見つかりません。";
	public static final String ERROR_LOAD_FAILED = "読み込みに失敗しました。";
	public static final String ERROR_SAVE_FAILED = "保存に失敗しました。";

	public static final KeyStroke SHORTCUT_UNDO = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
	public static final KeyStroke SHORTCUT_REDO = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
	public static final KeyStroke SHORTCUT_DELETE = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
	public static final KeyStroke SHORTCUT_ESC = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
	public static final KeyStroke SHORTCUT_LOAD = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
	public static final KeyStroke SHORTCUT_SAVE = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);

	public static final String TOOLTIP_UNDO = "元に戻す（Ctrl+Z）";
	public static final String TOOLTIP_REDO = "やり直し（Ctrl+Y）";
	public static final String TOOLTIP_DELETE = "選択中の長方形を削除（Delete）";
	public static final String TOOLTIP_LOAD = "ファイルを開く（Ctrl+O）";
	public static final String TOOLTIP_SAVE = "ファイルを保存（Ctrl+S）";

	private Constants() {
	} // インスタンス化防止
}
