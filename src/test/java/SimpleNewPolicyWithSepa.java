import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SimpleNewPolicyWithSepa {

    public static ChromeOptions options;
    public static WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public NewInsurancePage newInsurancePage;
    public PaymentPage paymentPage;
    public SummeryPage summeryPage;

    @BeforeTest
    public void Setup() {
        options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        newInsurancePage = new NewInsurancePage(driver);
        paymentPage = new PaymentPage(driver);
        summeryPage = new SummeryPage(driver);
        driver.get("https://insurance-manager.sb-qa-candidatetask.sisu.sh/login");
    }

    @AfterTest
    void TearDown() {
        driver.close();
    }

    @Test
    void Login() {
        Assert.assertTrue(loginPage.isLoginPageOpened(), "Login page was not opened");
        loginPage.preformLogin("testsellingpartner5@simplesurance.de", "TestSellingPartner5Pass");
        Assert.assertTrue(homePage.isHomePageOpen(), "Login failed - Home page was not opened");
    }

    @Test
    void OpenNewPolicyForm() {
        homePage.createNewPolicy();
        Assert.assertTrue(newInsurancePage.isInsuranceFormOpen(), "Failed to open new insurance form");
    }

    @Test
    void FillNewPolicyForm() {
        newInsurancePage.fillInsuranceFormFirstStep();
        newInsurancePage.submitInsuranceFormFirstStep();
        Assert.assertTrue(newInsurancePage.isInsuranceSecondStepIsOpen(), "Failed to open Payment page");
        newInsurancePage.fillInsuranceFormSecondStep();
        newInsurancePage.submitInsuranceFormSecondStep();
        newInsurancePage.clickCheckboxThirdStep();
        newInsurancePage.clickCreateInsuranceButton();
    }

    @Test
    void SubmitSepaDetails() {
        Assert.assertTrue(paymentPage.isPaymentFormOpen());
        paymentPage.choosePaymentMethod("STRIPE.DIRECT_DEBIT");
        paymentPage.clickContinueButton();
        Assert.assertTrue(paymentPage.isSepaFormOpen());
        paymentPage.fillSepaForm();
        paymentPage.clickContinueButton();
    }

    @Test
    void VerifyPolicyCreation() {
        String policyID = summeryPage.getNewPolicyId();
        summeryPage.returnToHomePage();
        Assert.assertTrue(homePage.isHomePageOpen());
        homePage.searchByPolicyNum(policyID);
        Assert.assertEquals(homePage.isTRContainsPolicy(), policyID);
    }
}

