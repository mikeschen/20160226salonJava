import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Add Client to Stylist");
  }

    @Test
  public void StylistIsCreatedTest() {
  goTo("http://localhost:4567/");
  fill("#stylistname").with("Floyd");
  submit(".btn-info");
  assertThat(pageSource()).contains("Floyd");
  }

  @Test
  public void allClientsDisplayDescriptionOnStylistPage() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Client firstClient = new Client("Jill", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("John", myStylist.getId());
    secondClient.save();
    String StylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(StylistPath);
    assertThat(pageSource()).contains("Jill");
    assertThat(pageSource()).contains("John");
  }
}
