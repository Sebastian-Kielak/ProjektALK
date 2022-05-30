package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressPage {
    private WebDriver driver;
    @FindBy (name = "processAddress")
    private WebElement proceedButton;
    @FindBy (name = "message")
    private WebElement textArea;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void addMessage(String message){
    this.textArea.sendKeys(message);
    }

    public ShippingPage clickProceedButton(){
        this.proceedButton.click();
        return new ShippingPage(driver);
    }
}
