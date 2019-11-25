package nz.net.osnz.demos.jersey;

import bathe.BatheBooter;
import org.junit.Test;

import java.io.File;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
public class BatheRunner {

  @Test
  public void run() {
    if (!new File("src/test/java").exists()) {
      throw new RuntimeException("Please ensure this test is run in the home directory of the project.");
    }

    // this ensures it understands we are in "dev" move, we aren't a bundled jar file - it also ensures
    // we get the test class loader
    new BatheBooter().runWithLoader(
      getClass().getClassLoader(),
      null,
      App.class.getName(),
      new String[]{
        "-Pclasspath:/app.properties",
        "-Pclasspath:/app-test.properties",
      });
  }


}


