package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingBtn;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;
    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeProductBtn;
    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> productsList;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> productsPrice;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShoppingBtn() {
        wait.waitForElementToBeClickable(continueShoppingBtn);
        continueShoppingBtn.click();
    }

    public CheckoutPage clickCheckoutBtn() {
        wait.waitForElementToBeClickable(checkoutBtn);
        act.pauseUsingAction(checkoutBtn, 1000);

        return new CheckoutPage(driver);
    }

    public void clickRemoveProductBtn() {
        wait.waitForElementToBeClickable(removeProductBtn);
        removeProductBtn.click();
    }

    public List<WebElement> getProductsList() {
        wait.waitForVisibilityAllElements(productsList);
        return productsList;
    }
}
