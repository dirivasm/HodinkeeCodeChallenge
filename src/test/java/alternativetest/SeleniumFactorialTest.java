package alternativetest;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class SeleniumFactorialTest extends SeleniumBaseTest {
    private final String factorialTxt = "#number";
    private final String result = "#resultDiv";


  @Test(dataProvider = "data", priority = 0)
  public void factorialTest(String testCondition, String number){
      //1. Check "The greatest factorial calculator!" is displayed
      cc.getElementWithText("The greatest factorial calculator!");

      //2. Set the number and click on Calculate!
      cc.setValue(driver.findElement(By.cssSelector(factorialTxt)),number);
      cc.clickButton("Calculate!");

      //Prepare actual vs expected results
      String resultText = cc.getElementWithText(format("The factorial of %s is:",number)).getText();

      //String resultText = $(result).shouldHave(text(format("The factorial of %s is:",number))).getText();
      Double actual = extractNumberFromResult(resultText);
      Double expected = calculateFactorial(Double.parseDouble(number));

      //3. Verify calculated factorial
      assertEquals(actual,expected);
  }

  @DataProvider
  public Object[][] data(){
      return new Object[][]
              {       { "Zero", "0" },
                      { "Between 1 and 3 Digits", "8" },
                      { "Four or More Digits", "1009" }}; //Defect [Requirement was ambiguous when it comes to supported range]
  }

    @Test(dataProvider = "negativeData", priority = 1)
    public void factorialNegativeTest(String testCondition, String data){

        //1. Set the number and click on Calculate!
        cc.setValue(driver.findElement(By.cssSelector(factorialTxt)),data);
        cc.clickButton("Calculate!");

        //2. Verify "Please enter an integer" message is displayed
        cc.getElementWithText("Please enter an integer");
    }

    @DataProvider
    public Object[][] negativeData(){
        return new Object[][]
                {       { "Negative Integers", "-5" }, //Defect [Factorial should only support Natural Numbers]
                        { "Decimals", "25.32" },
                        { "Non Numeric", "Alphabet" },
                        { "Empty", "" }};
    }

  private Double calculateFactorial(Double number){
      if (number == 0)
          return 1D;
      else
          return number * calculateFactorial(number-1);
  }

  private Double extractNumberFromResult(String s){
        s = s.split(":")[1].trim();
        return Double.parseDouble(s);
  }

}
