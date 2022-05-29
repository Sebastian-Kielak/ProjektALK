import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.pages.pop.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValidationRegisterFormTest {

    public WebDriver driver;
    public MainPage mainPage;
    public SignInPage signInPage;
    public RegisterPage registerPage;
    public MainLoggedUserPage mainLoggedUserPage;
    public ContactUsPage contactUsPage;
    public HashMap<String, String> registerEmail = new HashMap<>();


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test(description = "Test sprawdzający walidacje formularza rejestracji")
    public void validationRegisterFormTest() {
// kroki testu
        Faker faker = new Faker(Locale.US);
        registerEmail.put("email", faker.internet().emailAddress());
        mainPage = new MainPage(driver).openPage();
        signInPage = mainPage.clickSignIn();
        signInPage.fillToRegister(registerEmail);
        registerPage = signInPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(elementToBeClickable(By.id("id_gender1")));
        registerPage.clearEmailInput();
        mainLoggedUserPage = registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getAlertText().contains("9 errors"));
    }
    @Test(description = "Test wysyłający prawidłowy formularz konstaktowy")
    public void sendCorrectContactUsForm(){
        mainPage = new MainPage(driver).openPage();
        mainPage.takeMainPageScreenshot("./images/MainPageScreenshot.png");
        contactUsPage = mainPage.clickContactUs();
        String pathToFile = System.getProperty("user.dir") + "/images/MainPageScreenshot.png";
        contactUsPage.fillContactForm("1", "abcdefg@wp.pl", "ORD_ER_12321", pathToFile, "Lorem impsum test message.");
        contactUsPage.clickSendButton();
        Assert.assertTrue(contactUsPage.getAlertText().contains("successfully sent"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
       driver.quit();
    }
}