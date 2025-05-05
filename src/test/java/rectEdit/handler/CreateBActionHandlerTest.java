package rectEdit.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import rectEdit.model.Rect;
import rectEdit.model.RectEditorModel;

public class CreateBActionHandlerTest {

	@Test
	public void testCreateRectBHandlerAddsRectangle() {
		RectEditorModel model = new RectEditorModel();
		ActionHandler handler = new CreateBActionHandler(model);

		handler.execute();

		assertEquals(1, model.getCurrentRectsCount());
		Rect rect = model.getRectanglesReadOnly().get(0);
		assertEquals(80, rect.getWidth());
		assertEquals(40, rect.getHeight());
	}
}
