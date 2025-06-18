package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browserInstance) {
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = browserInstance;
        }
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browser");
        }

        WebDriver webDriver = switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");  // optional: headless for CI
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                // Create unique user data dir
                String tempProfile = "/tmp/chrome-profile-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + tempProfile);

                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                // Add unique temp user-data-dir
                String tempProfile = "/tmp/edge-profile-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + tempProfile);

                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                // Add unique temp user-data-dir
                String tempProfile = "/tmp/firefox-profile-" + System.currentTimeMillis();
                options.addArguments("--user-data-dir=" + tempProfile);

                WebDriverManager.edgedriver().setup();
                yield new FirefoxDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        driver.set(webDriver);
        driver.get().manage().window().maximize();
        driver.get().manage().deleteAllCookies();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.get("timeout"))));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
