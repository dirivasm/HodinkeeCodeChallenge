package alternativetest;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import seleniumhelpers.CommonCommandsSelenium;

import java.util.concurrent.TimeUnit;


public class SeleniumBaseTest {
  protected CommonCommandsSelenium cc;
  protected WebDriver driver;
  private Config config;

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    driver = setConfiguration();
    cc = new CommonCommandsSelenium(driver);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(config.getInt("timeOut"),TimeUnit.MILLISECONDS);
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(){
    driver.get(config.getString("baseUrl"));
  }

  @AfterClass(alwaysRun = true)
  public void afterMethod() {
    driver.quit();
  }

  /**
   * This should be responsibility of a different class
   * Since this is a very small solution, I'm keeping this as an internal method
   */
  private WebDriver setConfiguration(){
    final String propertiesFile = "general.properties";
    config = ConfigFactory.load(propertiesFile);

    WebDriver driver = null;
    String browser = System.getProperty("browser");
    if(browser.equalsIgnoreCase("chrome")){
      System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
      driver = new ChromeDriver();
    }
    return driver;
  }

}
