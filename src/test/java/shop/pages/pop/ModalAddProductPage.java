package shop.pages.pop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalAddProductPage {
    private WebDriver driver;
    @FindBy (className = "icon-chevron-left")
    private WebElement continueShoppingIconButton;


    public ModalAddProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public SearchResultPage continueShopping(){
        this.continueShoppingIconButton.click();
        return new SearchResultPage(driver);
    }

    public WebElement buttonElement(){
        return this.continueShoppingIconButton;
    }

}
