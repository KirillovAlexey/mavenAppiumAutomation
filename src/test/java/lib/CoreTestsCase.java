package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.ios.WelcomePageObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestsCase extends TestCase {

    protected AppiumDriver driver;
    protected AppiumDriver driver2;

    //@BeforeClass
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        //driver2 = Platform.getInstance().getDriver2();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    //@AfterClass
    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int time) {
        driver.runAppInBackground(Duration.ofMillis(time));
    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }
}
