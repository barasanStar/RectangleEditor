package rectEdit.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;
import rectEdit.view.RectEditorView;

public class CreateRectAHandlerTest {

	@Test
	public void testCreateRectAHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel();
		RectEditorView view = new RectEditorView(model, null);
		CreateRectAHandler handler = new CreateRectAHandler(model, view);

		handler.execute();

		assertEquals(1, model.getRectanglesReadOnly().size());
		Rect rect = model.getRectanglesReadOnly().get(0);
		assertEquals(100, rect.getWidth());
		assertEquals(120, rect.getHeight());
	}
}
