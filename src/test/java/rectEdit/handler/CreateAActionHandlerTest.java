package rectEdit.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

public class CreateAActionHandlerTest {

	@Test
	public void testCreateRectAHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel();
		ActionHandler handler = new CreateAActionHandler(model);

		handler.execute();

		assertEquals(1, model.getCurrentRectsCount());
		Rect rect = model.getRectanglesReadOnly().get(0);
		assertEquals(100, rect.getWidth());
		assertEquals(120, rect.getHeight());
	}
}
