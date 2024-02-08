package Pages;

import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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

    public void filterTableByEmail(String emailAddress){
        DriverUtils.chooseNotRandomDropListItem(this.driver, ":r8:", 1);
        DriverUtils.clearAndSendKeys(this.driver.findElement(By.id(":rd:")), emailAddress + Keys.ENTER);
    }

    public boolean isPageFiltered(){
        List<WebElement> filters = this.driver.findElements(By.xpath("//*[@data-testid='CancelIcon']"));
        return !filters.isEmpty();
    }

    public void removePageFilters(){
        List<WebElement> filters = this.driver.findElements(By.xpath("//*[@data-testid='CancelIcon']"));
        for (WebElement filter : filters){
            filter.click();
        }
    }

    public int getNumberOfTableRows(){
        WebElement tableBody = this.driver.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public String isTRContainsPolicy(){
        WebElement tableBody = this.driver.findElement(By.tagName("tbody"));
        return tableBody.findElement(By.tagName("button")).getText();
    }

    public List<String> getAllPolicies(){
        WebElement tableBody = this.driver.findElement((By.tagName("tbody")));
        List<WebElement> policies = tableBody.findElements(By.tagName("button"));
        List<String> policiesInt = new ArrayList<>();
        for(WebElement policy : policies) {
            policiesInt.add(policy.getText());
        }
        return policiesInt;
    }

    public void sortTable(){
        WebElement sortData = this.driver.findElement(By.xpath("//*[@data-sort='POLICY_NUMBER']"));
        sortData.findElement(By.tagName("svg")).click();
    }

    public void openImportPage(){
        this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/a[3]/button")).click();
    }
}
