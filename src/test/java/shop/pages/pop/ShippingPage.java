package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {
    private WebDriver driver;
    @FindBy (name = "processCarrier")
    private WebElement proceedButton;
    @FindBy (id = "cgv")
    private WebElement checkbox;

    public ShippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickCheckbox(){
        this.checkbox.click();
    }

    public PaymentPage clickProceedButton(){
        this.proceedButton.click();
        return new PaymentPage(driver);
    }
}

