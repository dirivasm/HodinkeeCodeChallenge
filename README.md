# Code Challenge

Dependencies: 
Maven;
Java 11;
Selenide/Selenium;
TestNG
---
# Important Note
Solution was developed using Selenide (Which is a Selenium wrapper -> `https://selenide.org/`).
You will notice Selenium methods are enhanced by this wrapper.

e.g: `$(By.locator) is short for driver.findElement(By.locator)`

e.g: `Explicit waits and assertions are handled with should/shouldBe/shouldHave methods`

However, since the requested technology was Selenium, I also developed the solution using it.
Please refer to below project structure to understand the holistic solution:

For Selenium (Chrome only)
1. src/main/java/seleniumhelpers: `Contains CommonCommands & WaitHelper (To handle ExplicitWaits) classes`
2. src/test/java/alternativetest/SeleniumBaseTest: `Contains set up and configuration - Setting the system property and pointing to chromedriver.exe`
3. src/test/java/alternativetest/SeleniumFactorialTest: `Contains Factorial tests implemented with Selenium`
4. src/test/java/alternativetest/SeleniumNavigationTest: `Contains Navigation tests implemented with Selenium`

For Selenide (Chrome, Firefox, IE, Edge)
1. src/main/java/selenidehelpers: `Contains CommonCommands & ElementExtensions classes`
2. src/test/java/test/BaseTest: `Contains set up and configuration - Using Configuration class from Selenide`
3. src/test/java/test/FactorialTest: `Contains Factorial tests implemented with Selenide`
4. src/test/java/test/NavigationTest: `Contains Navigation tests implemented with Selenide`

PS: In conclusion, the only reason I developed this solution with both approaches (Selenide & Selenium):
1. Selenide is a Selenium wrapper (You can use Selenium with Selenide dependency)
2. Code looks cleaner and optimized

---
## To Run Test Execution as Maven project from IntelliJ IDE
1. Create Run configuration:
    a) select `Maven` template;
    b) put under Working Directory path to : `HodinkeeCodeChallenge`;
    c) put under Command line:  `clean test -Dbrowser=Chrome"`;
2. Select created configuration and click `Run` button from

## To Run Test Execution as Maven from command line
1. Open command line
1. Go to project's root (You should see pom.xml and testng.xml files)
3. Execute command:
    a) Chrome: `mvn clean test -Dbrowser=Chrome"`;
    b) Firefox: `mvn clean test -Dbrowser=Firefox"`;
    c) Edge: `mvn clean test -Dbrowser=Edge"`;
    d) IE: `mvn clean test -Dbrowser=IE"`;

# Bugs, Test Cases and API documentation
1. High level test cases: Open `High_Level_TC.pdf`

2. Reported Bugs: Open `Bug_Report_Summary.pdf`

3. API - Factorial endpoint documentation: Open `API_Doc.pdf`
