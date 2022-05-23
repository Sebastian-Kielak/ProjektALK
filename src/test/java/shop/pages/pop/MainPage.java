package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class MainPage {
    private WebDriver driver;
    @FindBy (className = "login")
    private WebElement signInButton;

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public MainPage openPage()
    {
        driver.get("http://www.automationpractice.com/");
        return new MainPage(driver);
    }

    public SignInPage clickSignIn()
    {
        this.signInButton.click();
        return new SignInPage(driver);
    }
}
