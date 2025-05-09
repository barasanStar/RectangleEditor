package rectangleEditor;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import rectangleEditor.handler.CreateAHandlerTest;
import rectangleEditor.history.HistoryManagerTest;
import rectangleEditor.model.BoardTest;
import rectangleEditor.model.RectEditorModelTest;
import rectangleEditor.model.RectTest;
import rectangleEditor.model.SelectionManagerTest;
import rectangleEditor.service.BoardServiceTest;
import rectangleEditor.service.RectServiceTest;
import rectangleEditor.service.ZOrderManagerTest;

@Suite
@SelectClasses({
		RectTest.class,
		BoardTest.class,
		RectServiceTest.class,
		BoardServiceTest.class,
		ZOrderManagerTest.class,
		SelectionManagerTest.class,
		HistoryManagerTest.class,
		RectEditorModelTest.class,
		CreateAHandlerTest.class
})

public class AllTests {
}
