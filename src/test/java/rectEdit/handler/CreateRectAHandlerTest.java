package rectEdit.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

public class CreateRectAHandlerTest {

	@Test
	public void testCreateRectAHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel(500, 500);
		CreateRectAHandler handler = new CreateRectAHandler(model);

		handler.execute();

		assertEquals(1, model.getRectangles().size());
		Rect rect = model.getRectangles().get(0);
		assertEquals(100, rect.getWidth());
		assertEquals(120, rect.getHeight());
	}
}
