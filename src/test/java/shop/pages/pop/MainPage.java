package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class MainPage {
    private WebDriver driver;
    private By signInButton = By.className("login");

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public MainPage openPage()
    {
        driver.get("http://www.automationpractice.com/");
        return new MainPage(driver);
    }

    public SignInPage clickSignIn()
    {
        driver.findElement(signInButton).click();
        return new SignInPage(driver);
    }
}
