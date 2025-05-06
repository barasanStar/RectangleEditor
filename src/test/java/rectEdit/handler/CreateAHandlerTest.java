package rectEdit.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

public class CreateAHandlerTest {

	@Test
	public void testCreateRectAHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel();
		ActionHandler handler = new CreateAHandler(model);

		handler.handle();

		assertEquals(1, model.getCurrentRectsCount());
		Rect rect = model.getRectanglesReadOnly().get(0);
		assertEquals(100, rect.getWidth());
		assertEquals(120, rect.getHeight());
	}
}
