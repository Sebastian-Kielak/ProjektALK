package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
    private WebDriver driver;
    @FindBy (className = "bankwire")
    private WebElement bankWireButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public ConfirmPaymentPage clickBankWireButton(){
        this.bankWireButton.click();
        return new ConfirmPaymentPage(driver);
    }
}