package shop.pages.pop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    private WebDriver driver;
    @FindBy (id = "id_contact")
    private WebElement subjectSelect;
    @FindBy (id = "email")
    private WebElement emailInput;
    @FindBy (id = "id_order")
    private WebElement orderReferenceInput;
    @FindBy (id = "fileUpload")
    private WebElement fileUploadInput;
    @FindBy (id = "message")
    private WebElement messageInput;
    @FindBy (id = "submitMessage")
    private WebElement sendButton;
    @FindBy (className = "alert")
    private WebElement alert;



    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillContactForm(String valueSubjectSelect, String email, String orderReference, String pathFile, String message) {
        Select subject = new Select(this.subjectSelect);
        subject.selectByValue(valueSubjectSelect);
        this.fileUploadInput.sendKeys(pathFile);
        this.emailInput.sendKeys(email);
        this.orderReferenceInput.sendKeys(orderReference);
        this.messageInput.sendKeys(message);
    }

    public String getAlertText(){
       return this.alert.getText();
    }

    public void clickSendButton(){
        this.sendButton.click();
    }
}