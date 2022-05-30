package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
    private WebDriver driver;
    @FindBy (linkText = "Proceed to checkout")
    private WebElement proceedButton;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public AddressPage clickProceedButton(){
        this.proceedButton.click();
        return new AddressPage(driver);
    }
}
