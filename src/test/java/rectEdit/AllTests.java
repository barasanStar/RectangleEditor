package rectEdit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import rectEdit.model.BoardTest;
import rectEdit.model.RectTest;
import rectEdit.service.BoardServiceTest;
import rectEdit.service.RectServiceTest;
import rectEdit.service.ZOrderManagerTest;

@Suite
@SelectClasses({
		RectTest.class,
		BoardTest.class,
		RectServiceTest.class,
		BoardServiceTest.class,
		ZOrderManagerTest.class
})

public class AllTests {
}
