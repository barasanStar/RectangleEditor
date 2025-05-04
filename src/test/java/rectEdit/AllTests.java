package rectEdit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import rectEdit.model.BoardTest;
import rectEdit.model.RectTest;
import rectEdit.service.BoardServiceTest;
import rectEdit.service.RectServiceTest;

@Suite
@SelectClasses({
		RectTest.class,
		BoardTest.class,
		RectServiceTest.class,
		BoardServiceTest.class
})

public class AllTests {
}
