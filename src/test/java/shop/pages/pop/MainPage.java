package shop.pages.pop;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;


public class MainPage {
    private WebDriver driver;
    @FindBy (className = "login")
    private WebElement signInButton;
    @FindBy (id = "contact-link")
    private WebElement contactUsButton;
    @FindBy (tagName = "body")
    private WebElement bodyElement;
    @FindBy (id = "search_query_top")
    private WebElement searchConsole;
    @FindBy (className = "button-search")
    private WebElement searchButton;

    public MainPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void takeMainPageScreenshot(String srcPath){
        try{
            File src = this.bodyElement.getScreenshotAs(OutputType.FILE);
            File path = new File(srcPath);
            FileHandler.copy(src, path);
        }
        catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
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

    public ContactUsPage clickContactUs()
    {
        this.contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public void searchProducts(String searchText){
        this.searchConsole.sendKeys(searchText);
    }

    public SearchResultPage clickSearchButton(){
        this.searchButton.click();
        return new SearchResultPage(driver);
    }
}
