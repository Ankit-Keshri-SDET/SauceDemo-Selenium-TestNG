package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(className = "login_logo")
    private WebElement logo;
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(name = "login-button")
    private WebElement loginBtn;
    @FindBy(tagName = "h3")
    private WebElement errorText;


    public LoginPage(WebDriver driver) {
        super(driver);

    }

    public void enterUserName(String username) {
        wait.waitForElementVisibility(userName);
        act.pauseUsingAction(userName, 1000);
        userName.sendKeys(username);
    }

    public void enterPassword(String pass) {
        wait.waitForElementVisibility(password);
        act.pauseUsingAction(password, 1000);
        password.sendKeys(pass);
    }

    public void clickLoginBtn() {
        wait.waitForElementToBeClickable(loginBtn);
        act.pauseUsingAction(loginBtn, 1000);
    }

    public String getLogoText() {
        wait.waitForElementVisibility(logo);
        return logo.getText();
    }

    public ProductsPage performLogin(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginBtn();

        return new ProductsPage(driver);
    }

    public void performLogin() {
        wait.waitForElementVisibility(userName);
        userName.clear();
        password.clear();
        loginBtn.click();
    }

    public String getErrorText() {
        wait.waitForElementVisibility(errorText);
        return errorText.getText();
    }
}
