package rectEdit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import rectEdit.handler.CreateAHandlerTest;
import rectEdit.history.HistoryManagerTest;
import rectEdit.model.BoardTest;
import rectEdit.model.RectEditorModelTest;
import rectEdit.model.RectTest;
import rectEdit.model.SelectionManagerTest;
import rectEdit.service.BoardServiceTest;
import rectEdit.service.RectServiceTest;
import rectEdit.service.ZOrderManagerTest;

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
