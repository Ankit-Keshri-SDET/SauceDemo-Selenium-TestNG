package tests;

import base.BasePage;
import enums.EnvType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected String appUrl;
    protected BasePage bp;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional String browser) {
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
        bp = new BasePage(driver);

        EnvType env = ConfigReader.getEnvironment();
        switch (env) {
            case DEVTEST1:
                appUrl = ConfigReader.get("qa_url");
                break;
            case ROBINQA:
                appUrl = ConfigReader.get("staging_url");
                break;
            case UAT:
                appUrl = ConfigReader.get("prod_url");
                break;
            default:
                throw new RuntimeException("Unknown environment: " + env);
        }
        driver.get(appUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
