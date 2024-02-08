import Pages.HomePage;
import Pages.ImportPage;
import Pages.LoginPage;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UploadFiles {

    public static WebDriver driver;
    public static ChromeOptions options;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static ImportPage importPage;


    @BeforeTest
    public void Setup(){
        options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setHttpProxy("http_proxy-url:port");
        proxy.setSslProxy("https_proxy-url:port");
        proxy.setNoProxy("no_proxy-var");

        options.setCapability("proxy", proxy);
        driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        importPage = new ImportPage(driver);
    }

    @AfterTest
    public void TearDown(){
        driver.close();
    }

    @Test
    public void Login() {
        driver.get("https://insurance-manager.sb-qa-candidatetask.sisu.sh/login");
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page was not opened");
        loginPage.preformLogin("testsellingpartner5@simplesurance.de", "TestSellingPartner5Pass");
        Assert.assertTrue(homePage.isHomePageOpen(), "Login failed - Home page was not opened");

    @Test
    public void ImportNewFile(){
        homePage.openImportPage();

        importPage.isImportPageOpen();
        importPage.uploadFile("src/test/resources/testdata.csv");
        Assert.assertEquals(importPage.isFileUploaded(), "testdata.csv");
        Assert.assertTrue(importPage.isTableIsDisplayed());

        importPage.uploadFile();
        importPage.waitForToast();
        importPage.getToastStatus();

}


    }

}
