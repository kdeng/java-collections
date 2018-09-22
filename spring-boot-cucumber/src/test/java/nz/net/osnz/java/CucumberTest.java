package nz.net.osnz.java;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "classpath:features")
public class CucumberTest {


}
