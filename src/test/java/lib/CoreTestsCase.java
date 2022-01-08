package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.ios.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestsCase extends TestCase {

    protected RemoteWebDriver driver;

    //@BeforeClass
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        //driver2 = Platform.getInstance().getDriver2();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiPageForMobileWeb();
    }

    //@AfterClass
    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else
            System.out.println("Method rotateScreenPortrait() does nothing for platform"
                    + Platform.getInstance().getPlatformVar());
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else
            System.out.println("Method rotateScreenLandscape() does nothing for platform"
                    + Platform.getInstance().getPlatformVar());
    }

    protected void backgroundApp(int time) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofMillis(time));
        } else
            System.out.println("Method backgroundApp(int time) does nothing for platform"
                    + Platform.getInstance().getPlatformVar());
    }

    protected void openWikiPageForMobileWeb() {
        if (Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        } else System.out.println("Method openWikiPageForMobileWeb() does nothing for platform"
                + Platform.getInstance().getPlatformVar());
    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }
}
