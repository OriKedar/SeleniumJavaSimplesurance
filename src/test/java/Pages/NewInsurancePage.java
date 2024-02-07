package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static Utilities.DriverUtils.*;

public class NewInsurancePage {

    public WebDriver driver;


    public NewInsurancePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isInsuranceFormOpen(){
        return this.driver.findElement(By.id("country-select-label")).isDisplayed();
    }

    public void fillInsuranceFormFirstStep(){
        chooseNotRandomDropListItem(this.driver, "country-select", 4);
        chooseRandomDropListItem(this.driver, "product-name-select");
        chooseRandomDropListItem(this.driver, "tariff-name-select");
        chooseRandomDropListItem(this.driver, "category-name-select");
        chooseRandomDropListItem(this.driver, "duration-select");
        chooseRandomDropListItem(this.driver, "frequency-select");
        chooseRandomDropListItem(this.driver, "class-name-select");
        clearAndSendKeys(this.driver.findElement(By.id("input-createCertificate_serialNumber")), "746390237");
        clearAndSendKeys(this.driver.findElement(By.id("input-createCertificate_deviceName")), "Simple form test");
        clearAndSendKeys(this.driver.findElement(By.name("invoiceNumber")), "43432432f4f");
    }

    public void submitInsuranceFormFirstStep(){
        WebElement submitButton = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[5]/div/div[3]/button"));
        waitForElementToBeClickable(this.driver, submitButton);
        submitButton.click();
    }

    public boolean isInsuranceSecondStepIsOpen(){
        return this.driver.findElement(By.name("firstName")).isDisplayed();
    }

    public void fillInsuranceFormSecondStep(){
        clearAndSendKeys(this.driver.findElement(By.name("firstName")), "ori");
        clearAndSendKeys(this.driver.findElement(By.name("lastName")), "test");
        clearAndSendKeys(this.driver.findElement(By.name("email")), "oritest@email.com");
        clearAndSendKeys(this.driver.findElement(By.name("streetName")), "Diagon Alley");
        clearAndSendKeys(this.driver.findElement(By.name("streetNumber")), "59");
        clearAndSendKeys(this.driver.findElement(By.name("zip")), "10001");
        clearAndSendKeys(this.driver.findElement(By.name("city")), "London");
        clearAndSendKeys(this.driver.findElement(By.name("country")), "GB");
    }

    public void submitInsuranceFormSecondStep(){
        WebElement submitButton = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[11]/div/div[3]/button"));
        waitForElementToBeClickable(this.driver, submitButton);
        submitButton.click();
    }

    public void clickCheckboxThirdStep(){
        WebElement confirm = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[1]/div[35]/div/div"));
        List<WebElement> checkboxes = confirm.findElements(By.tagName("input"));
        for (WebElement element : checkboxes){
            element.click();
        }
    }

    public void clickCreateInsuranceButton(){
        clickElement(this.driver, this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/div[2]/div/div[3]/button")));
    }
}
