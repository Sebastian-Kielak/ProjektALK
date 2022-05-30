package shop.pages.pop;

import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    private WebDriver driver;
    // lokalizatory do asercji -> ValidationRegisterFormTest
    @FindBy (className = "alert")
    private WebElement alert;
    // lokalziatory dla danych klienta
    @FindBy (id = "id_gender1")
    private WebElement male;
    @FindBy (id = "id_gender2")
    private WebElement female;
    @FindBy (id = "customer_firstname")
    private WebElement custName;
    @FindBy (id = "customer_lastname")
    private WebElement custLastName;
    @FindBy (id = "passwd")
    private WebElement password;
    @FindBy (id = "days")
    private WebElement dayOfBirth;
    @FindBy (id = "months")
    private WebElement monthOfBirth;
    @FindBy (id = "years")
    private WebElement yearOfBirth;
    @FindBy (id = "email")
    private WebElement email;
    // lokalizatory dla danych adresowych
    @FindBy (id = "firstname")
    private WebElement firstNameAddress;
    @FindBy (id = "lastname")
    private WebElement lastNameAddress;
    @FindBy (id = "address1")
    private WebElement address;
    @FindBy (id = "city")
    private WebElement city;
    @FindBy (id = "id_state")
    private WebElement state;
    @FindBy (id = "postcode")
    private WebElement zipCode;
    @FindBy (id = "phone_mobile")
    private WebElement mobilePhone;
    // lokalizaotr przycisku rejestracji
    @FindBy (id = "submitAccount")
    private WebElement buttonRegister;
    // zmienne prywatne do przechowania maila i hasłą
    private String savePasswd;
    private String saveEmail;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillRegisterForm(HashMap<String, String> data){
        // losowanie płci dla false = mężczyzna, true = kobieta
        drawAndFillGender();
        this.custName.sendKeys(data.get("custName"));
        this.custLastName.sendKeys(data.get("custLastName"));
        this.password.sendKeys(data.get("password"));
        Select day = new Select(this.dayOfBirth);
        day.selectByValue(data.get("dayOfBirth"));
        Select month = new Select(this.monthOfBirth);
        month.selectByValue(data.get("monthOfBirth"));
        Select year = new Select(this.yearOfBirth);
        year.selectByValue(data.get("yearOfBirth"));
        this.firstNameAddress.sendKeys(data.get("custName"));
        this.lastNameAddress.sendKeys(data.get("custLastName"));
        this.address.sendKeys(data.get("address"));
        this.city.sendKeys(data.get("city"));
        Select chooseState = new Select(this.state);
        chooseState.selectByValue(data.get("state"));
        this.zipCode.sendKeys(data.get("zipCode"));
        this.mobilePhone.sendKeys(data.get("mobilePhone"));

        savePasswd = data.get("password");
        saveEmail = data.get("email");

        System.out.println("Password: " + savePasswd);
        System.out.println("Email: " + saveEmail);
    }


    public void drawAndFillGender(){
        Random liczba = new Random();
        boolean gender = liczba.nextBoolean();
        if (gender == false){

            this.male.click();
        }
        else{
            this.female.click();
        }
    }

    public WebElement genderElement(){
        return this.male;
    }

    public void clearEmailInput(){
    this.email.clear();
    }

    public MainLoggedUserPage clickRegisterButton(){
        this.buttonRegister.click();
        return new MainLoggedUserPage(driver);
    }

    public String getAlertText(){
       return this.alert.getText();
    }

    public WebElement alertElement(){
        return this.alert;
    }
}
