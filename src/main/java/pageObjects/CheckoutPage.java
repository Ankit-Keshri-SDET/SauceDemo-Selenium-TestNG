package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstName;
    @FindBy(id = "last-name")
    private WebElement lastName;
    @FindBy(id = "postal-code")
    private WebElement zipCode;
    @FindBy(name = "cancel")
    private WebElement cancelBtn;
    @FindBy(xpath = "//*[@value='Continue']")
    private WebElement continueBtn;
    @FindBy(xpath = "//*[@class='title']")
    private WebElement checkoutHeader;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstname) {
        wait.waitForElementVisibility(firstName);
        act.pauseUsingAction(firstName, 1000);
        firstName.sendKeys(firstname);
    }

    public void enterLastName(String lastname) {
        wait.waitForElementVisibility(lastName);
        act.pauseUsingAction(firstName, 1000);
        lastName.sendKeys(lastname);
    }

    public void enterZipCode(String zipcode) {
        wait.waitForElementVisibility(zipCode);
        act.pauseUsingAction(firstName, 1000);
        this.zipCode.sendKeys(zipcode);
    }

    public PaymentPage clickContinueBtn() {
        wait.waitForElementToBeClickable(continueBtn);
        act.pauseUsingAction(continueBtn, 500);
        return new PaymentPage(driver);
    }

    public void confirmCheckout(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        clickContinueBtn();
    }

    public String getCheckoutHeaderText() {
        wait.waitForElementVisibility(checkoutHeader);
        return checkoutHeader.getText();
    }

    public void clickCancelBtn() {
        wait.waitForElementToBeClickable(cancelBtn);
        cancelBtn.click();
    }
}
