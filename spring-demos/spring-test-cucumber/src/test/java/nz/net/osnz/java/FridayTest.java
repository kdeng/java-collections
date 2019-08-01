package nz.net.osnz.java;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class FridayTest {

  LocalDate today;
  boolean result;

  @Given("^today is Sunday")
  public void todayIsSunday() {
    today = LocalDate.of(2018, 9, 23);
  }

  @When("^I ask whether it's Friday yet")
  public void invokeIsFriday() {
    result = DateUtil.isFriday(today);
  }

  @Then("^I should be told \"Nope\"")
  public void resultShouldBeFalse() {
    Assert.assertFalse(result);
  }

}
