package test;

import com.codeborne.selenide.Configuration;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import selenidehelpers.CommonCommands;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
  protected CommonCommands cc = new CommonCommands();

  @BeforeSuite(alwaysRun = true)
  public void setUp(){
    setConfiguration();
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    open("");
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(final ITestResult testResult) {
    if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == -1)
      closeWebDriver();
  }

  /**
   * This should be responsibility of a different class
   * Since this is a very small solution, I'm keeping this as an internal method
   */
  private void setConfiguration(){
    final String propertiesFile = "general.properties";
    Config config =
            ConfigFactory.load(propertiesFile);

    //There is no need to set chromedriver, firefoxdriver, etc. path because Selenide handles this internally.
    String browser = System.getProperty("browser");
    Configuration.browser = browser;
    if(browser.equalsIgnoreCase("IE"))
      Configuration.clickViaJs = true;

    System.out.println("BASE URL:"+config.getString("baseUrl"));

    Configuration.baseUrl = config.getString("baseUrl");
    Configuration.timeout = config.getInt("timeOut");
    Configuration.headless = config.getBoolean("isBrowserHeadless");
    Configuration.reportsFolder = config.getString("reportFolder");
    Configuration.startMaximized = true;
    Configuration.screenshots = true;

  }

}
