package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {
    private WebDriver driver;

    public ActionUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void pauseUsingAction(WebElement element, long millis) {
        Actions actions = new Actions(driver);
        actions.pause(java.time.Duration.ofMillis(millis))
                .click(element)
                .perform();
    }
}
