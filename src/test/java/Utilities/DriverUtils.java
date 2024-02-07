package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DriverUtils {


    public static void chooseRandomDropListItem(WebDriver driver, String filedName){

        try {
            WebElement element = driver.findElement(By.id(filedName));
            waitForElementToBeClickable(driver, element);
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to open drop list for " + filedName + ": " + e);
        }
        int randomNum = 0;
        List<WebElement> elements = driver.findElements(By.tagName("li"));
        if (elements.size() > 1) {
            randomNum = ThreadLocalRandom.current().nextInt(0, elements.size() - 1);
        }
        try {
        elements.get(randomNum).click();
            } catch (Exception e) {
            System.out.println("Fail to choose random list item for " + filedName + ": " + e);
        }
    }

    public static void chooseNotRandomDropListItem(WebDriver driver, String filedName, Integer option){
        try {
            WebElement element = driver.findElement(By.id(filedName));
            waitForElementToBeClickable(driver, element);
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to open drop list for " + filedName + ": " + e);
        }
        List<WebElement> elements = driver.findElements(By.tagName("li"));
        try {
            elements.get(option).click();
        } catch (Exception e) {
            System.out.println("Fail to choose random list item for " + filedName + ": " + e);
        }
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickElement(WebDriver driver, WebElement element){
        waitForElementToBeClickable(driver, element);
        element.click();
    }

    public static void clearAndSendKeys(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
