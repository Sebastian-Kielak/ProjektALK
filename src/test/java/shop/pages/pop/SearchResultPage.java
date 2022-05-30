package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
    private WebDriver driver;
    @FindBy (className = "icon-th-list")
    private WebElement viewListButton;
    @FindBy (xpath = "//*[@data-id-product=5]")
    private WebElement addCart1;
    @FindBy (className = "login")
    private WebElement signInButton;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void changeViewOnList(){
    this.viewListButton.click();
    }

    public ModalAddProductPage addProduct (){
        this.addCart1.click();
        return new ModalAddProductPage(driver);
    }

    public SignInPage clickSignInButton(){
        this.signInButton.click();
        return new SignInPage(driver);
    }

}