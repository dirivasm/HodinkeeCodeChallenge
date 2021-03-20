package test;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static java.lang.String.format;
import static org.openqa.selenium.By.linkText;
import static org.testng.Assert.assertEquals;
import static selenidehelpers.ElementExtensions.highlightElement;

public class NavigationTest extends BaseTest{
    @Test(dataProvider = "linksData", priority = 1)
    public void verifyLinks(String link,String expectedUrl, String expectedText){

        //1. Set the number and click on link
        highlightElement($(linkText(link)).shouldBe(visible)).click();

        //2. Verify landing page is expectedUrl
        assertEquals(url(),expectedUrl);

        //3. Verify internal info. E.g:
        $(byText(expectedText)).shouldBe(visible);

    }

    @DataProvider
    public Object[][] linksData(){
        return new Object[][]
                {       { "Terms and Conditions" ,"http://qainterview.pythonanywhere.com/terms",  "This is the terms and conditions document. We are not yet ready with it. Stay tuned!"}, //Defect [Wrong text is displayed in this link]
                        { "Privacy","http://qainterview.pythonanywhere.com/privacy", "This is the privacy document. We are not yet ready with it. Stay tuned!" }, //Defect [Wrong text is displayed in this link]
                        { "Qxf2 Services","https://qxf2.com/?utm_source=qa-interview&utm_medium=click&utm_campaign=From%20QA%20Interview", "QA for startups" }};
    }

}
