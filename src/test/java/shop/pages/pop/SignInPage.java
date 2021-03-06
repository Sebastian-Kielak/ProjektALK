package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;


public class SignInPage {
    private WebDriver driver;
    // lokalziatory elementów do rejestracji
    @FindBy (id = "email_create")
    private WebElement inputEmail;
    @FindBy (id = "SubmitCreate")
    private WebElement buttonCreateAccnt;
    // lokalizatory elementów do logowania
    @FindBy (id = "email")
    private WebElement inputLoginEmail;
    @FindBy (id = "passwd")
    private WebElement inputLoginPassword;
    @FindBy (id = "SubmitLogin")
    private WebElement buttonSignIn;


    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillToRegister(HashMap<String, String> data){
        this.inputEmail.sendKeys(data.get("email"));
    }

    public RegisterPage clickRegisterButton(){
        this.buttonCreateAccnt.click();
        return new RegisterPage(driver);
    }

    public void fillToLogin(String login, String password){
        this.inputLoginEmail.sendKeys(login);
        this.inputLoginPassword.sendKeys(password);
    }

    public MainLoggedUserPage clickSignInButton(){
        this.buttonSignIn.click();
        return new MainLoggedUserPage(driver);
    }
}
