import groovy.transform.CompileStatic
import nz.ac.auckland.agent.AgentLoader
import nz.ac.auckland.common.testrunner.GroupAppsUnitTestRunner
import nz.ac.auckland.war.TestRunner
import org.junit.runner.RunWith

/**
 * Created with IntelliJ IDEA.
 * User: kdeng
 * Date: 8/11/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
@CompileStatic
@RunWith(GroupAppsUnitTestRunner)
class testRunner extends TestRunner {
    static {
        AgentLoader.findAgentInClasspath("avaje-ebeanorm-agent", "debug=0")
//        MultiModuleConfigScanner.loadIntoSystemProperties();
    }
}
