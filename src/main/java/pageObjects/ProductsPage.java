package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//button[contains(@id,'add')]")
    private List<WebElement> addProductBtn;
    @FindBy(xpath = "//div[contains(@class,'inventory_item_name ')]")
    private List<WebElement> productsLink;
    @FindBy(xpath = "//div[@class='inventory_item_price")
    private List<WebElement> productPrice;
    @FindBy(xpath = "//span[@class='title']")
    private WebElement productsHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProductByName(String productName) {
        int index = 0;
        for (WebElement product : productsLink) {
            if (product.getText().equalsIgnoreCase(productName)) {
                wait.waitForElementToBeClickable(productsLink.get(index));
                product.click();
                break;
            } else {
                index++;
            }
        }
    }

    public void addRandomProduct() {
        int randomNumber = (int) (Math.random() * addProductBtn.size());
        System.out.println(randomNumber);
        addProductBtn.get(randomNumber).click();
    }
}
