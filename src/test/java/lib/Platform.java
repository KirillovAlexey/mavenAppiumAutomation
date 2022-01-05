package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

//@RunWith(value = Parameterized.class)
public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    //private int port = 4723;
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    //private String APPIUM_URL = "http://127.0.0.1:" + port + "/wd/hub";

    private static Platform instance;

    private Platform() {

    }


//    @Parameterized.Parameters
//
//    public static Collection<String> data() {
//
//        String[] data = new String[]{
//                "http://127.0.0.1:4723/wd/hub",
//                "http://127.0.0.1:4724/wd/hub",
//                "http://127.0.0.1:4725/wd/hub"};
//        return Arrays.asList(data);
//    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        //port++;
        return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
//        if (this.isAndroid()) {
//            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
//        } else if (this.isIOS()) {
//            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
//        } else {
//            throw new Exception("Cannot detect type of the driver. Platform value: " + this.getPlatformVar());
//        }
    }

//    public AppiumDriver getDriver2() throws Exception {
//        URL URL = new URL(APPIUM_URL.replace("4723", "4724"));
//        port++;
//        return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
//        if (this.isAndroid()) {
//            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
//        }
//    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:\\Users\\amoroz\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("androidNaturalOrientation", true);
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app",
                "C:\\Users\\amoroz\\Desktop\\JavaAppiumAutomation\\apks\\Wikipedia.app");

        return capabilities;
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
}
