package selenidehelpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static selenidehelpers.ElementExtensions.highlightElement;


/**
 * This Class is designed to keep repeatable actions which can be reused in several tests
 */
public class CommonCommands{

  /**
   * Perform click on button or tab.
   * @param btnName This is a visible button
   * @return SelenideElement detected button.
   */
  public SelenideElement clickBtn(String btnName) {
    SelenideElement btn =
          $$("button,.btn")
            .filter(text(btnName))
            .first();
    highlightElement(btn.shouldBe(visible, enabled)).click();
    return btn;
  }

  /**
   * Fill input type fields with any given value
   * @param input
   * @param val
   * @return
   */
  public SelenideElement setValue(SelenideElement input,String val) {
    highlightElement(input.shouldBe(visible)).setValue(val);
    return input;
  }

}
