package shop.pages.pop;

import java.util.HashMap;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    // lokalziatory dla danych klienta
    private By male = By.id("id_gender1");
    private By female = By.id("id_gender2");
    private By custName = By.id("customer_firstname");
    private By custLastName = By.id("customer_lastname");
    private By password = By.id("passwd");
    private By dayOfBirth = By.id("days");
    private By monthOfBirth = By.id("months");
    private By yearOfBirth = By.id("years");
    // lokalizatory dla danych adresowych
    private By firstNameAddress = By.id("firstname");
    private By lastNameAddress = By.id("lastname");
    private By address = By.id("address1");
    private By city = By.id("city");
    private By state = By.id("id_state");
    private By zipCode = By.id("postcode");
    private By mobilePhone = By.id("phone_mobile");
    // lokalizaotr przycisku rejestracji
    private By buttonRegister = By.id("submitAccount");
    // zmienne prywatne do przechowania maila i hasłą
    private String savePasswd;
    private String saveEmail;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegisterForm(HashMap<String, String> data){
        // losowanie płci dla false = mężczyzna, true = kobieta
        drawAndFillGender();
        driver.findElement(custName).sendKeys(data.get("custName"));
        driver.findElement(custLastName).sendKeys(data.get("custLastName"));
        driver.findElement(password).sendKeys(data.get("password"));
        Select day = new Select(driver.findElement(dayOfBirth));
        day.selectByValue(data.get("dayOfBirth"));
        Select month = new Select(driver.findElement(monthOfBirth));
        month.selectByValue(data.get("monthOfBirth"));
        Select year = new Select(driver.findElement(yearOfBirth));
        year.selectByValue(data.get("yearOfBirth"));
        driver.findElement(firstNameAddress).sendKeys(data.get("custName"));
        driver.findElement(lastNameAddress).sendKeys(data.get("custLastName"));
        driver.findElement(address).sendKeys(data.get("address"));
        driver.findElement(city).sendKeys(data.get("city"));
        Select chooseState = new Select(driver.findElement(state));
        chooseState.selectByValue(data.get("state"));
        driver.findElement(zipCode).sendKeys(data.get("zipCode"));
        driver.findElement(mobilePhone).sendKeys(data.get("mobilePhone"));

        savePasswd = data.get("password");
        saveEmail = data.get("email");

        System.out.println("Password: " + savePasswd);
        System.out.println("Email: " + saveEmail);
    }


    public void drawAndFillGender(){
        Random liczba = new Random();
        boolean gender = liczba.nextBoolean();
        if (gender == false){
            driver.findElement(male).click();
        }
        else{
            driver.findElement(female).click();
        }
    }

    public MainLoggedUserPage clickRegisterButton(){
        driver.findElement(buttonRegister).click();
        return new MainLoggedUserPage(driver);
    }
}
