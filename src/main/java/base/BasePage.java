package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.CartPage;
import utils.ActionUtils;
import utils.ConfigReader;
import utils.WaitUtils;

public class BasePage {
    public WebDriver driver;
    public WaitUtils wait;
    public ActionUtils act;

    @FindBy(id = "react-burger-menu-btn")
    protected WebElement hamburgerMenuBtn;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;
    @FindBy(id = "about_sidebar_link")
    private WebElement aboutBtn;
    @FindBy(className = "app_logo")
    private WebElement websiteLogo;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    @FindBy(className = "footer_copy")
    private WebElement footerMessage;

    public final String SOCIAL_MEDIA_URL_PREFIX = "//li//a[contains(@href,'";
    public final String SOCIAL_MEDIA_URL_SUFFIX = ")]";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver, Long.parseLong(ConfigReader.get("waitTimeoutSeconds")));
        act = new ActionUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickHamburgerMenuBtn() {
        wait.waitForElementToBeClickable(hamburgerMenuBtn);
        hamburgerMenuBtn.click();
    }

    public void clickLogoutButton() {
        wait.waitForElementToBeClickable(logoutBtn);
        act.pauseUsingAction(logoutBtn, 2000);
    }

    public void clickAboutButton() {
        wait.waitForElementToBeClickable(aboutBtn);
        aboutBtn.click();
    }

    public CartPage clickCartButton() {
        wait.waitForElementToBeClickable(cartLink);
        cartLink.click();

        return new CartPage(driver);
    }

    public int getCartQuantity() {
        wait.waitForElementVisibility(cartBadge);
        return Integer.parseInt(cartBadge.getText());
    }

    public String getWebsiteLogoText() {
        wait.waitForElementVisibility(websiteLogo);
        return websiteLogo.getText();
    }

    public WebElement getFooterLinks(String socialMediaLink) {
        String path = SOCIAL_MEDIA_URL_PREFIX + socialMediaLink + SOCIAL_MEDIA_URL_SUFFIX;
        return driver.findElement(By.xpath(path));
    }


}
