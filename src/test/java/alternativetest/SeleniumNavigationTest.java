package alternativetest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SeleniumNavigationTest extends SeleniumBaseTest {


    @Test(dataProvider = "linksData")
    public void verifyLinks(String link,String expectedUrl, String expectedText){

        //1. Set the number and click on link
        cc.clickLinkElement(link);

        //2. Verify landing page is expectedUrl
        assertEquals(driver.getCurrentUrl(),expectedUrl);

    }

    @DataProvider
    public Object[][] linksData(){
        return new Object[][]
                {       { "Terms and Conditions" ,"http://qainterview.pythonanywhere.com/terms",  "This is the terms and conditions document. We are not yet ready with it. Stay tuned!"}, //Defect [Wrong text is displayed in this link]
                        { "Privacy","http://qainterview.pythonanywhere.com/privacy", "This is the privacy document. We are not yet ready with it. Stay tuned!" }, //Defect [Wrong text is displayed in this link]
                        { "Qxf2 Services","https://qxf2.com/?utm_source=qa-interview&utm_medium=click&utm_campaign=From%20QA%20Interview", "QA for startups" }};
    }




}
