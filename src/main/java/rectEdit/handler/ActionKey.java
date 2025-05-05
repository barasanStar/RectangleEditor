package rectEdit.handler;

public enum ActionKey {
	CREATE_RECT_A("定型長方形A作成"), CREATE_RECT_B("定型長方形B作成"), DELETE("削除"), MOVE("移動"), Z_ORDER_FRONT("最前面へ"), Z_ORDER_BACK(
			"最背面へ"), Z_ORDER_FORWARD("一つ前面へ"), Z_ORDER_BACKWARD("一つ背面へ");

	private final String displayName;

	ActionKey(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
