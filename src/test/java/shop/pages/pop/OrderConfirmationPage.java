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

public class OrderConfirmationPage{
    private WebDriver driver;
    @FindBy (className = "box")
    private WebElement confirmOrderBox;


    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String returnTextnfirmOrder(){
       return this.confirmOrderBox.getText();
    }


    public void takeConfirmOrderScrnShot(String srcPath){
        try{
            File src = this.confirmOrderBox.getScreenshotAs(OutputType.FILE);
            File path = new File(srcPath);
            FileHandler.copy(src, path);
        }
        catch (IOException ex) {
            Reporter.log(ex.getMessage(), true);
        }
    }


}
