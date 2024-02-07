package Pages;

import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    public WebDriver driver;

    public PaymentPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPaymentFormOpen(){
     DriverUtils.waitForElementToBeVisible(this.driver, this.driver.findElement(By.tagName("button")));
     return this.driver.findElement(By.tagName("button")).isDisplayed();
    }

    public void choosePaymentMethod(String paymentMethod){
        this.driver.findElement(By.xpath("//input[@value='" + paymentMethod +"']")).click();
    }

    public void clickContinueButton(){
        DriverUtils.clickElement(this.driver, this.driver.findElement(By.tagName("button")));
    }

    public boolean isCreditCardFormOpen(){
        return this.driver.findElement(By.name("cardnumber")).isDisplayed();
    }
    public void fillCreditCardDetails(){
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("cardnumber")), "4242424242424242");
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("exp-date")),"0125");
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("cvc")),"354");
    }

    public boolean isSepaFormOpen(){
        return this.driver.findElement(By.name("accountHolder")).isDisplayed();
    }

    public void fillSepaForm(){
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("accountHolder")), "Harry Potter");
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("email")), "harry@hogwarts.com");
        WebElement iframe = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div[1]/div/div[1]/div/div/div[3]/div/div/div/div/iframe"));
        driver.switchTo().frame(iframe);
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.name("iban")), "DE89370400440532013000");
        driver.switchTo().defaultContent();
        this.driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    }
}