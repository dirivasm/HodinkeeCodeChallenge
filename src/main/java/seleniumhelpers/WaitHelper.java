package seleniumhelpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenidehelpers.ElementExtensions;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;


/**
 * This Class is designed to keep repeatable actions which can be reused in several tests
 */
public class WaitHelper {

  private WebDriverWait wait;

  public WaitHelper(WebDriver driver){
    wait = new WebDriverWait(driver, 5);
  }

  /**
   * Waits until element is clickable on the page
   * @param webElement
   * @return
   */
  public WebElement waitUntilElementClickable(WebElement webElement){
      WebElement waitUntil = wait.until(ExpectedConditions.elementToBeClickable(webElement));
      return waitUntil;
  }

  /**
   * Waits until the element is visible on the page
   * @param webElement
   * @return
   */
  public WebElement waitUntilElementVisible(WebElement webElement){
      WebElement waitUntil = wait.until(ExpectedConditions.visibilityOf(webElement));
      return waitUntil;
  }

    /**
     * Waits until text is present in element
     * @param webElement
     * @Param text
     * @return
     */
    public Boolean waitUntilTextPresent(WebElement webElement,String text){
        Boolean waitUntil = wait.until(ExpectedConditions.textToBePresentInElement(webElement,text));
        return waitUntil;
    }
}
