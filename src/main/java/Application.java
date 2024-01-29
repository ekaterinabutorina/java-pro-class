import runner.TestRunner;
import task.Box;

import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        TestRunner.runTests(Box.class);
    }

}
