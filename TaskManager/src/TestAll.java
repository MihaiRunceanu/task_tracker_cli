import domain.TaskTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import repository.TaskRepositoryTest;
import service.TaskServiceTest;


@Suite
@SelectClasses({TaskTest.class, TaskRepositoryTest.class, TaskServiceTest.class})
public class TestAll {
}
