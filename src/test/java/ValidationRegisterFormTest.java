import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import shop.pages.pop.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;


public class ValidationRegisterFormTest {

    public WebDriver driver;
    public MainPage mainPage;
    public SignInPage signInPage;
    public RegisterPage registerPage;
    public MainLoggedUserPage mainLoggedUserPage;
    public ContactUsPage contactUsPage;
    public SearchResultPage searchResultPage;
    public ModalAddProductPage modalAddProductPage;
    public ShoppingCartPage shoppingCartPage;
    public AddressPage addressPage;
    public ShippingPage shippingPage;
    public PaymentPage paymentPage;
    public ConfirmPaymentPage confirmPaymentPage;
    public OrderConfirmationPage orderConfirmationPage;
    public HashMap<String, String> registerEmail = new HashMap<>();
    public HashMap<String, String> registerData = new HashMap<>();


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test(description = "Test przechodzący przez pełen proces sprzedaży")
    public void orderProcessTest() throws IOException, CsvValidationException
    {
        mainPage = new MainPage(driver).openPage();
        mainPage.searchProducts("Woman");
        searchResultPage = mainPage.clickSearchButton();
        searchResultPage.changeViewOnList();
        modalAddProductPage = searchResultPage.addProduct();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(modalAddProductPage.buttonElement()));
        searchResultPage = modalAddProductPage.continueShopping();
        signInPage = searchResultPage.clickSignInButton();
        // wczytywanie z pliku .csv danych do logowania, wartości do warunku asercji
        CSVReader csv = new CSVReader(new FileReader("./data/RegisteredAccount.csv"));
        String[] line;
        line = csv.readNext();
        String loginEmail = line[0];
        String password = line[1];
        String userName = line[2];
        // dalszy proces testu
        signInPage.fillToLogin(loginEmail, password);
        mainLoggedUserPage = signInPage.clickSignInButton();
        Assert.assertEquals(mainLoggedUserPage.getLoginUser(), userName);
        shoppingCartPage = mainLoggedUserPage.clickShoppingCartButton();
        addressPage = shoppingCartPage.clickProceedButton();
        addressPage.addMessage("Lorem impsum przykładowa uwaga do zamówienia.");
        shippingPage = addressPage.clickProceedButton();
        shippingPage.clickCheckbox();
        paymentPage = shippingPage.clickProceedButton();
        confirmPaymentPage = paymentPage.clickBankWireButton();
        orderConfirmationPage = confirmPaymentPage.clickConfirmOrderButton();
        orderConfirmationPage.takeConfirmOrderScrnShot("./images/OrderConfirmScreenshot.png");
        Assert.assertTrue(orderConfirmationPage.returnTextnfirmOrder().contains("Your order on My Store is complete."));
    }

    @Test(description = "Test sprawdzajacy walidacje formularza rejestracji", enabled = false)
    public void validationRegisterFormTest() {
// kroki testu
        Faker faker = new Faker(Locale.US);
        registerEmail.put("email", faker.internet().emailAddress());
        mainPage = new MainPage(driver).openPage();
        signInPage = mainPage.clickSignIn();
        signInPage.fillToRegister(registerEmail);
        registerPage = signInPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
        registerPage.clearEmailInput();
        mainLoggedUserPage = registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.getAlertText().contains("9 errors"));
    }

    @Test(description = "Test wysylajacy prawidlowy formularz konstaktowy", enabled = false)
    public void sendCorrectContactUsForm(){
        mainPage = new MainPage(driver).openPage();
        mainPage.takeMainPageScreenshot("./images/MainPageScreenshot.png");
        contactUsPage = mainPage.clickContactUs();
        String pathToFile = System.getProperty("user.dir") + "/images/MainPageScreenshot.png";
        contactUsPage.fillContactForm("1", "abcdefg@wp.pl", "ORD_ER_12321", pathToFile, "Lorem impsum test message.");
        contactUsPage.clickSendButton();
        Assert.assertTrue(contactUsPage.getAlertText().contains("successfully sent"));
    }

    @Test (description = "Test sprawdzajacy poprawne logowanie", enabled = false)
    public void loginTest() throws IOException, CsvValidationException {
        // wczytywanie z pliku .csv danych do logowania, wartości do warunku asercji
        CSVReader csv = new CSVReader(new FileReader("./data/RegisteredAccount.csv"));
        String[] line;
        line = csv.readNext();
        String loginEmail = line[0];
        String password = line[1];
        String userName = line[2];
        // kroki testu
        mainPage = new MainPage(driver).openPage();
        signInPage = mainPage.clickSignIn();
        signInPage.fillToLogin(loginEmail, password);
        mainLoggedUserPage = signInPage.clickSignInButton();
        Assert.assertEquals(mainLoggedUserPage.getLoginUser(), userName);
    }

    @Test(description = "Test sprawdzający prawidłowy proces rejestracji", enabled = false)
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
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
        registerPage.fillRegisterForm(registerData);
        mainLoggedUserPage = registerPage.clickRegisterButton();
        Assert.assertEquals(mainLoggedUserPage.getLoginUser(),registerData.get("custName") + " " + registerData.get("custLastName"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        //driver.quit();
    }
}