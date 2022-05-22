package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DoneRegisterPage {
    private WebDriver driver;
    private By userName = By.className("account");

    public DoneRegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginUser(){
        return driver.findElement(userName).getText();

    }
}