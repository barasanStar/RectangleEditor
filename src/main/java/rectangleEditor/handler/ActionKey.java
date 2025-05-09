package rectangleEditor.handler;

public class ActionKey {
	// --- ファイル操作系 ---
	public static final String LOAD_FROM_TEXT = "ファイルを開く";
	public static final String SAVE_TO_TEXT = "ファイルへ保存";
	public static final String SAVE_AS_IMAGE = "画像として保存";

	// --- 編集系 ---
	public static final String UNDO = "元に戻す";
	public static final String REDO = "やり直し";

	// --- 長方形操作系 ---
	public static final String CREATE_A = "定型A作成";
	public static final String CREATE_B = "定型B作成";
	public static final String CREATE_RANDOM = "ランダム作成";
	public static final String DELETE = "削除";
	public static final String DELETE_ALL = "全削除";
	public static final String MOVE = "移動";
	public static final String EXPAND = "拡大縮小";
	public static final String COLOR = "色変更";
	public static final String Z_ORDER_FRONT = "最前面へ";
	public static final String Z_ORDER_BACK = "最背面へ";
	public static final String Z_ORDER_FORWARD = "一つ前面へ";
	public static final String Z_ORDER_BACKWARD = "一つ背面へ";

	// インスタンス化防止
	private ActionKey() {
	}
}
