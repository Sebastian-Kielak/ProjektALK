package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainLoggedUserPage {
    private WebDriver driver;
    @FindBy (className = "account")
    private WebElement userName;

    public MainLoggedUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getLoginUser(){
        return this.userName.getText();
    }
}