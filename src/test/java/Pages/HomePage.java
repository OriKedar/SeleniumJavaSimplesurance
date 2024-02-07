package Pages;

import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isHomePageOpen(){
        return this.driver.findElement(By.className("jss34")).isDisplayed();
    }
    public void createNewPolicy(){
        WebElement createNewPolicy = this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/a[2]/button"));
        createNewPolicy.isDisplayed();
        createNewPolicy.click();
    }

    public void searchByPolicyNum(String policyId){
        DriverUtils.chooseNotRandomDropListItem(this.driver, ":r8:", 0);
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.id(":rd:")), policyId + Keys.ENTER);
    }

    public String isTRContainsPolicy(){
        WebElement tableBody = this.driver.findElement(By.tagName("tbody"));
        return tableBody.findElement(By.tagName("button")).getText();
    }
}
