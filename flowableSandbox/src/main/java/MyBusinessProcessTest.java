import org.flowable.engine.test.Deployment;
import org.flowable.engine.test.FlowableTestCase;
import org.flowable.task.api.Task;
import org.h2.tools.Server;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MyBusinessProcessTest extends FlowableTestCase {

    @Deployment
    public void testSimpleProcess() throws SQLException {

        runtimeService.startProcessInstanceByKey("simpleProcess");

        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
