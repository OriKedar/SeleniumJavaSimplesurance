package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isLoginPageOpened(){
        WebElement formBody = this.driver.findElement(By.className("jss13"));
        return formBody.isDisplayed();
    }

    public void FillUserName(String userName){
        WebElement userNameField = this.driver.findElement(By.id("login_username"));
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    public void FillPassword(String password){
        WebElement passwordField = this.driver.findElement(By.id("login_password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void SubmitLogin(){
        this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[4]/button")).click();
    }

    public void preformLogin(String userName, String password){
        FillUserName(userName);
        FillPassword(password);
        SubmitLogin();
    }
}