package rectEdit.handler;

public enum ActionKey {
	CREATE_RECT_A("定型長方形A作成"), CREATE_RECT_B("定型長方形B作成"), DELETE("削除"), MOVE("移動");

	private final String displayName;

	ActionKey(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
