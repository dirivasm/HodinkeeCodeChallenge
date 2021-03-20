package seleniumhelpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenidehelpers.ElementExtensions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertTrue;


/**
 * This Class is designed to keep repeatable actions which can be reused in several tests
 */
public class CommonCommandsSelenium {

  WebDriver driver;
  WaitHelper wait;

  public CommonCommandsSelenium(WebDriver driver){
    this.driver = driver;
    wait = new WaitHelper(driver);
  }

  /**
   * Perform click on button using xpath only
   *
   * @param btnName This is a visible button or tab tet.
   * @return WebElement detected button.
   */
  public WebElement clickButton(String btnName) {
    String btnLocator = format("//*[contains(@class,'btn') and contains(text(),'%s')]",btnName);
    WebElement element = driver.findElement(xpath(btnLocator));
    wait.waitUntilElementClickable(element).click();
    return element;
  }

  /**
   * Fill input type fields with any given value
   * @param input
   * @param val
   * @return
   */
  public WebElement setValue(WebElement input,String val) {
    wait.waitUntilElementVisible(input).sendKeys(val);
    return input;
  }

  /**
   * Perform click on button using xpath only
   *
   * @param aName This is a visible link
   * @return WebElement detected 'a' element
   */
  public WebElement clickLinkElement(String aName) {
    WebElement element = driver.findElement(linkText(aName));
    wait.waitUntilElementClickable(element).click();
    return element;
  }

  /**
   * Search an element by its text
   * @param text
   * @return
   */
  public WebElement getElementWithText(String text) {
    WebElement we = null;
    try{
      String xpathTextLocator = format("//*[contains(text(),'%s')]",text);
      Boolean isPresent = wait.waitUntilTextPresent(driver.findElement(xpath(xpathTextLocator)),text);
      assertTrue(isPresent);
      we = driver.findElement(xpath(xpathTextLocator));
    }catch (Exception e){
      assertTrue(false,"No element was found with: '"+text+"' text.");
    }
    return we;
  }

}
