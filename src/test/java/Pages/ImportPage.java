package Pages;

import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class ImportPage {

    public WebDriver driver;

    public ImportPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isImportPageOpen(){
        DriverUtils.waitForElementToBeExists(this.driver, By.id("input-csv"));
    }

    public void uploadFile(String filePath){
        File uploadFile = new File(filePath);
        this.driver.findElement(By.id("input-csv")).sendKeys(uploadFile.getAbsolutePath());
    }

    public String isFileUploaded(){
        WebElement fileName = this.driver.findElement(By.tagName("h6"));
        return fileName.getText();
    }

    public boolean isTableIsDisplayed(){
        return this.driver.findElement(By.tagName("table")).isDisplayed();
    }

    public void uploadFile(){
        DriverUtils.clickElement(this.driver, this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div[2]/button")));
    }

    public void waitForToast(){
        DriverUtils.waitForElementToBeVisible(this.driver, this.driver.findElement(By.id("client-snackbar")));
    }

    public void getToastStatus(){
        String toastMessage = this.driver.findElement(By.id("client-snakbar")).getText();
        System.out.println(toastMessage);
        if (toastMessage.contains("Error")){
            System.out.println("error needed" + toastMessage);
        } else {
            System.out.println("file was uploaded successfully");
        }
    }
}
