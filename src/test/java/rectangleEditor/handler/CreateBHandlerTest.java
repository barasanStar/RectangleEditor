package rectangleEditor.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectangleEditor.handler.ActionHandler;
import rectangleEditor.handler.rect.CreateBHandler;
import rectangleEditor.model.Rect;
import rectangleEditor.model.RectEditorModel;

public class CreateBHandlerTest {

	@Test
	public void testCreateRectBHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel();
		ActionHandler handler = new CreateBHandler(model);

		handler.handle();

		assertEquals(1, model.getCurrentRectsCount());
		Rect rect = model.getRectanglesReadOnly().get(0);
		assertEquals(80, rect.getWidth());
		assertEquals(40, rect.getHeight());
	}
}
