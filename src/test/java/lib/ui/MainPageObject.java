package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;
    protected List<WebElement> list = new ArrayList();

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void clickElementAndToRightUpperCorner(String locator, String message) {
        WebElement element = this.waitForElementPresent(locator + "/..", message);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();

        int pointToClickX = (rightX + width) - 3;
        int pointToClickY = middleY;
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }

    public void swipeElementToLeft(String locator, String message) {
        WebElement element = waitForElementPresent(
                locator,
                message,
                10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(rightX, middleY));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
        if (Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(leftX, middleY));
        } else {
            int offset_x = (element.getSize().getWidth() * -1);
            action.moveTo(PointOption.point(offset_x, 0));
        }
        action.release();
        action.perform();
    }

    public void swipeUpQuick() {
        swipeUp(300);
    }

    public void swipeUpToFindElement(String locator, String message, int max_swipes) {
        int already_swiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            swipeUpQuick();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find elements " + message, 0);
                break;
            }
        }
    }

    //Прокрутка экрана до подвала на iOS
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        while (this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    //Вспомогательный метод прокрутки до подвала на iOS
    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(
                locator,
                "Cannot find element by locator",
                1).
                getLocation().
                getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public WebElement assertElementHasText(String locator, String expected, String message) {
        WebElement webElement = waitForElementPresent(
                locator,
                "Cannot find this text in search" + expected,
                15
        );
        Assert.assertEquals(message, expected, webElement.getText());
        return webElement;
    }

    public void checkCountUrls(List list) {
        Assert.assertTrue("Количество статей менее либо равно '1'", list.size() > 1);
    }

    public void checkWordInputAllUrls(List<WebElement> list, String searchWord) {
        for (WebElement element : list) {
            Assert.assertTrue("В указанной ссылке отсутствует искомое слово в названии",
                    element.getText().contains(searchWord));
        }
    }

    public int getAmountElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List<WebElement> getFindsElements(String locator) {
        By by = this.getLocatorByString(locator);
        list = driver.findElements(by);
        return list;
    }

    public void assertElementsNotPresent(String locator, String message) {
        int amount_of_elements = getAmountElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element " + locator + " supposed to be not present";
            throw new AssertionError(default_message + " " + message);
        }
    }

    public WebElement assertElementPresentWithoutTimeout(String locator) {
        WebElement element = waitForElementPresent(
                locator,
                "Cannot find title in Article",
                0);
        return element;
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator);
    }
}