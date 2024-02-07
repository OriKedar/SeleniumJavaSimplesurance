import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FilterAndSorting {

    public static ChromeOptions options;
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static HomePage homePage;


    @BeforeTest
    public void Setup(){
        options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterTest
    public void TearDown(){
        driver.close();
    }

    @Test
    public void Login(){
        driver.get("https://insurance-manager.sb-qa-candidatetask.sisu.sh/login");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page was not opened");
        loginPage.preformLogin("testsellingpartner5@simplesurance.de", "TestSellingPartner5Pass");
        Assert.assertTrue(homePage.isHomePageOpen(), "Login failed - Home page was not opened");
    }

    @Test
    public void FilterTableTest(){
        while(homePage.isPageFiltered()){
            homePage.removePageFilters();
        }
        homePage.filterTableByEmail("sort@test.com");
        Assert.assertTrue(homePage.isPageFiltered());
        Assert.assertEquals(homePage.getNumberOfTableRows(), 5, "filter test failed, the number of table rows is not as expected");
    }

    @Test
    public void SortTableTest(){
        List<String> rawPoliciesList = homePage.getAllPolicies();
        Collections.sort(rawPoliciesList);
        homePage.sortTable();
        Assert.assertEquals(homePage.getAllPolicies(), rawPoliciesList);
    }
}
