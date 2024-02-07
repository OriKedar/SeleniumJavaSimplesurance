package Pages;

import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SummeryPage {


    public WebDriver driver;

    public SummeryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNewPolicyId(){
        return this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div")).getText();
    }

    public void returnToHomePage(){
        WebElement btnDiv = this.driver.findElement(By.className("jss13"));
        DriverUtils.clickElement(this.driver, btnDiv.findElement(By.tagName("button")));
    }
}
