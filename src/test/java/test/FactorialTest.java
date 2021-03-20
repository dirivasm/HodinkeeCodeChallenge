package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

public class FactorialTest extends BaseTest{
  private final String factorialTxt = "#number";
  private final String result = "#resultDiv";

  @Test(dataProvider = "data", priority = 0)
  public void factorialTest(String testCondition, String number){

      //1. Check "The greatest factorial calculator!" is displayed
      $(byText("The greatest factorial calculator!")).shouldBe(visible);

      //2. Set the number and click on Calculate!
      cc.setValue($(factorialTxt),number);
      cc.clickBtn("Calculate!");

      //Prepare actual vs expected results
      String resultText = $(result).shouldHave(text(format("The factorial of %s is:",number))).getText();
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
        cc.setValue($(factorialTxt),data);
        cc.clickBtn("Calculate!");

        //2. Verify "Please enter an integer" message is displayed
        $(result).shouldHave(text("Please enter an integer"));

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
