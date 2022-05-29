import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.pages.pop.MainLoggedUserPage;
import shop.pages.pop.MainPage;
import shop.pages.pop.SignInPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class OrderProcessTest{

    public WebDriver driver;
    public MainPage mainPage;
    public SignInPage signInPage;
    public MainLoggedUserPage mainLoggedUserPage;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "Test sprawdzający pełny proces sprzedaży")
    public void orderProcessTest() throws IOException, CsvValidationException {
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


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
