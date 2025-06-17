package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.LoggerUtil;

public class DataDrivenTests extends BaseTest {
    Logger logger = LoggerUtil.getLogger(LoginPageTest.class);
    public LoginPage lp;
    public ProductsPage pp;

    @Test(dataProvider = "loginData", dataProviderClass = utils.DataProviderUtils.class)
    public void loginDDT(String username, String password) {
        logger.info("Starting test : successLogin");
        lp = new LoginPage(driver);
        logger.info("Driver initialized successfully");
        logger.info("Entering credentials ....");
        lp.performLogin(username, password);
        if (driver.getCurrentUrl().contains("inventory.html")) {
            pp = new ProductsPage(driver);
            logger.info("Clicking on Hamburger menu ...");
            pp.clickHamburgerMenuBtn();
            logger.info("Clicking on Logout button ...");
            pp.clickLogoutButton();
            Assert.assertEquals(lp.getLogoText(), "Swag Labs", "Not Login page ..");
            logger.info("Test ended ... successLogin");
        } else {
            Assert.assertTrue(lp.getErrorText().contains("been locked out"));
        }
    }

}
