package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SignInPage {
    private WebDriver driver;
    // lokalziatory elementów do rejestracji
    private By inputEmail = By.id("email_create");
    private By buttonCreateAccnt = By.id("SubmitCreate");
    // lokalizatory elementów do logowania
    private By inputLoginEmail = By.id("email");
    private By inputLoginPassword = By.id("passwd");
    private By buttonSignIn = By.id("SubmitLogin");


    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillToRegister(HashMap<String, String> data){
        driver.findElement(inputEmail).sendKeys(data.get("email"));
    }

    public RegisterPage clickRegisterButton(){
        driver.findElement(buttonCreateAccnt).click();
        return new RegisterPage(driver);
    }

    public void fillToLogin(String login, String password){
        driver.findElement(inputLoginEmail).sendKeys(login);
        driver.findElement(inputLoginPassword).sendKeys(password);
    }

    public MainLoggedUserPage clickSignInButton(){
        driver.findElement(buttonSignIn).click();
        return new MainLoggedUserPage(driver);
    }
}
