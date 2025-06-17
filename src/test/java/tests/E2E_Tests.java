package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.LoggerUtil;

public class E2E_Tests extends BaseTest {
    Logger logger = LoggerUtil.getLogger(LoginPageTest.class);
    public LoginPage lp;
    public ProductsPage pp;
    public ProductPage pp1;
    public CartPage cp;
    public CheckoutPage chp;
    public PaymentPage pyp;
    public ConfirmationPage cop;


    @Test(groups = {"smoke"})
    public void purchaseOneProduct() throws Exception {
        logger.info("Starting test : successLogin");
        lp = new LoginPage(driver);
        logger.info("Driver initialized successfully");
        logger.info("Entering user credentials ....");
        pp = lp.performLogin(ConfigReader.get("username"), ConfigReader.get("password"));
        logger.info("Clicking on Product Link ....");
        pp.clickOnProductByName(ConfigReader.get("prod1"));
        pp1 = new ProductPage(driver);
        logger.info("Clicking on Add To Cart Button ....");
        pp1.clickOnAddToCartBtn();
        logger.info("Clicking on Cart Icon ....");
        cp = bp.clickCartButton();
        chp = cp.clickCheckoutBtn();
        ExcelUtils.setExcelFile(ConfigReader.get("userDetailsSheetPath"), ConfigReader.get("sheetName1"));
        logger.info("Entering FirstName ....");
        chp.enterFirstName(ExcelUtils.getCellData(1, 0));
        logger.info("Entering LastName ....");
        chp.enterLastName(ExcelUtils.getCellData(1, 1));
        logger.info("Entering ZipCode ....");
        chp.enterZipCode(ExcelUtils.getCellData(1, 2));
        pyp = chp.clickContinueBtn();
        cop = pyp.clickFinishBtn();

        Assert.assertTrue(cop.getThankYouMessage().contains("Thank you for your order"));

    }

    @Test(groups = {"smoke"})
    public void purchaseTwoProduct() throws Exception {
        logger.info("Starting test : successLogin");
        lp = new LoginPage(driver);
        logger.info("Driver initialized successfully");
        logger.info("Entering user credentials ....");
        pp = lp.performLogin(ConfigReader.get("username"), ConfigReader.get("password"));
        logger.info("Clicking on Product Link ....");
        pp.clickOnProductByName(ConfigReader.get("prod1"));
        pp.clickOnProductByName(ConfigReader.get("prod2"));
        pp1 = new ProductPage(driver);
        logger.info("Clicking on Add To Cart Button ....");
        pp1.clickOnAddToCartBtn();
        logger.info("Clicking on Cart Icon ....");
        cp = bp.clickCartButton();
        chp = cp.clickCheckoutBtn();
        ExcelUtils.setExcelFile(ConfigReader.get("userDetailsSheetPath"), ConfigReader.get("sheetName1"));
        logger.info("Entering FirstName ....");
        chp.enterFirstName(ExcelUtils.getCellData(3, 0));
        logger.info("Entering LastName ....");
        chp.enterLastName(ExcelUtils.getCellData(3, 1));
        logger.info("Entering ZipCode ....");
        chp.enterZipCode(ExcelUtils.getCellData(3, 2));
        pyp = chp.clickContinueBtn();
        cop = pyp.clickFinishBtn();

        Assert.assertTrue(cop.getThankYouMessage().contains("Thank you for your order"));

    }

    @Test
    public void purchaseRandomProduct() throws Exception {
        logger.info("Starting test : successLogin");
        lp = new LoginPage(driver);
        logger.info("Driver initialized successfully");
        logger.info("Entering user credentials ....");
        pp = lp.performLogin(ConfigReader.get("username"), ConfigReader.get("password"));
        logger.info("Clicking on Any Random Product ....");
        pp.addRandomProduct();
        logger.info("Clicking on Cart Icon ....");
        cp = bp.clickCartButton();
        chp = cp.clickCheckoutBtn();
        ExcelUtils.setExcelFile(ConfigReader.get("userDetailsSheetPath"), ConfigReader.get("sheetName1"));
        logger.info("Entering FirstName ....");
        chp.enterFirstName(ExcelUtils.getCellData(2, 0));
        logger.info("Entering LastName ....");
        chp.enterLastName(ExcelUtils.getCellData(2, 1));
        logger.info("Entering ZipCode ....");
        chp.enterZipCode(ExcelUtils.getCellData(2, 2));
        pyp = chp.clickContinueBtn();
        cop = pyp.clickFinishBtn();

        Assert.assertTrue(cop.getThankYouMessage().contains("Thank you for your order"));

    }
}
