package rectangleEditor.model;

public interface RectEditorModelListener {
	void onRectsChanged(String operationLogMessage);

	void onSelectionChanged(String operationLogMessage);
}
