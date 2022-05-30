package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPaymentPage{
    private WebDriver driver;
    @FindBy (xpath = "//*[@id=\"cart_navigation\"]/button")
    private WebElement confirmMyOrderButton;


    public ConfirmPaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public OrderConfirmationPage clickConfirmOrderButton(){
        this.confirmMyOrderButton.click();
        return new OrderConfirmationPage(driver);
    }
}