package tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.ConfigReader;
import utils.LoggerUtil;

public class LoginPageTest extends BaseTest {
    Logger logger = LoggerUtil.getLogger(LoginPageTest.class);
    public LoginPage lp;
    public ProductsPage pp;

    @Test(groups = {"sanity"})
    public void successLogin() throws InterruptedException {
        logger.info("Starting test : successLogin");
        lp = new LoginPage(driver);
        logger.info("Driver initialized successfully");
        logger.info("Entering user credentials ....");
        pp = lp.performLogin(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText(), "Products", "Incorrect Page header ...");
        logger.info("Clicking on Hamburger menu ...");
        lp.clickHamburgerMenuBtn();
        logger.info("Clicking on Logout button ...");
        lp.clickLogoutButton();
        Assert.assertEquals(lp.getLogoText(), "Swag Labs", "Not Login page ..");
        logger.info("Test ended ... successLogin");
    }

    @Test(groups = {"regression"})
    public void errorUserCheck() {
        logger.info("Starting test: errorUserCheck");
        lp = new LoginPage(driver);
        logger.info("LoginPage object created");
        lp.performLogin(ConfigReader.get("error_username"), ConfigReader.get("password"));
        logger.info("Performed login with locked user credentials");
        Assert.assertTrue(lp.getErrorText().contains("been locked out"));
        logger.info("Verified locked out error message");
        logger.info("Test ended ... errorUserCheck");
    }

    @Test(groups = {"regression", "smoke"})
    public void incorrectCredentials() {
        logger.info("Starting test: incorrectCredentials");
        lp = new LoginPage(driver);
        logger.info("LoginPage object created");
        lp.performLogin("incorrect_user", ConfigReader.get("password"));
        logger.info("Performed login with incorrect credentials");
        Assert.assertTrue(lp.getErrorText().contains("Username and password do not match"));
        logger.info("Verified incorrect credentials error message");
        logger.info("Test ended ... incorrectCredentials");
    }

    @Test(groups = {"regression"})
    public void emptyCredentials() {
        logger.info("Starting test: emptyCredentials");
        lp = new LoginPage(driver);
        logger.info("LoginPage object created");
        lp.performLogin();
        logger.info("Performed login without entering any credentials");
        Assert.assertTrue(lp.getErrorText().contains("Username is required"));
        logger.info("Verified empty credentials error message");
        logger.info("Test ended ... emptyCredentials");
    }
}
