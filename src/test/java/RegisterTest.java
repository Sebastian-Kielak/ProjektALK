import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.pages.pop.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;

public class RegisterTest {

    public WebDriver driver;
    public MainPage mainPage;
    public SignInPage signInPage;
    public RegisterPage registerPage;
    public MainLoggedUserPage mainLoggedUserPage;
    public HashMap<String, String> registerData = new HashMap<>();


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void RegisterTest() {
        //generowanie danych do rejestracji -> HashMap data
        Faker faker = new Faker(Locale.US);
        registerData.put("custName", faker.name().firstName());
        registerData.put("custLastName", faker.name().lastName());
        registerData.put("email", faker.internet().emailAddress());
        registerData.put("password", faker.internet().password());
        registerData.put("address", faker.address().streetAddress());
        registerData.put("city", faker.address().city());
        registerData.put("zipCode", faker.address().zipCode());
        while(registerData.get("zipCode").length() > 5) {
            registerData.put("zipCode", faker.address().zipCode());
        }
        registerData.put("mobilePhone", faker.phoneNumber().cellPhone());
        registerData.put("dayOfBirth", String.valueOf(faker.random().nextInt(1,27)));
        registerData.put("monthOfBirth", String.valueOf(faker.random().nextInt(1,12)));
        registerData.put("yearOfBirth", String.valueOf(faker.random().nextInt(1900,2003)));
        registerData.put("state", String.valueOf(faker.random().nextInt(1,53)));

        // kroki testu
        mainPage = new MainPage(driver).openPage();
        signInPage = mainPage.clickSignIn();
        signInPage.fillToRegister(registerData);
        registerPage = signInPage.clickRegisterButton();
        registerPage.fillRegisterForm(registerData);
        mainLoggedUserPage = registerPage.clickRegisterButton();
        Assert.assertEquals(mainLoggedUserPage.getLoginUser(),registerData.get("custName") + " " + registerData.get("custLastName"));
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
