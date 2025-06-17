package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//*[@id='back-to-products']")
    private WebElement backToProductsBtn;
    @FindBy(tagName = "h2")
    private WebElement thankYouMessage;
    @FindBy(className = "complete-text")
    private WebElement orderCompleteText;
    @FindBy(xpath = "//*[@class='title']")
    private WebElement completeHeader;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getThankYouMessage() {
        wait.waitForElementVisibility(thankYouMessage);
        return thankYouMessage.getText();
    }
}
