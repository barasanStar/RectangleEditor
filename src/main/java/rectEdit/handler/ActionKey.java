package rectEdit.handler;

public enum ActionKey {
	LOAD_FROM_TEXT("ファイルを開く"),
	SAVE_TO_TEXT("ファイルへ保存"),
	SAVE_AS_IMAGE("画像として保存"),
	CREATE_A("定型A作成"),
	CREATE_B("定型B作成"),
	CREATE_RANDOM("ランダム作成"),
	DELETE("削除"),
	DELETE_ALL("全削除"),
	MOVE("移動"),
	EXPAND("拡大縮小"),
	COLOR("色変更"),
	Z_ORDER_FRONT("最前面へ"),
	Z_ORDER_BACK("最背面へ"),
	Z_ORDER_FORWARD("一つ前面へ"),
	Z_ORDER_BACKWARD("一つ背面へ");

	private final String displayName;

	ActionKey(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
