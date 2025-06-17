package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(id = "cancel")
    private WebElement cancelBtn;
    @FindBy(id = "finish")
    private WebElement finishBtn;
    @FindBy(xpath = "//*[@class='title']")
    private WebElement overViewHeader;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmationPage clickFinishBtn() {
        wait.waitForElementToBeClickable(finishBtn);
        act.pauseUsingAction(finishBtn, 1000);

        return new ConfirmationPage(driver);
    }

}
