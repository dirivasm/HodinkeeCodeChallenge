package selenidehelpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class ElementExtensions {

  public static SelenideElement highlightElement(SelenideElement element) {
    String js = "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');";
    Selenide.executeJavaScript(js, element);
    return element;
  }
}
