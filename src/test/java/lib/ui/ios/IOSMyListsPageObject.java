package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    //Переписать локаторы для iOS
    static {
        ARTICLE_BY_TITLE = "xpath://*[contains(@text, '{TITLE}')]";
        OPEN_BOOKMARK = "xpath://*[contains(@text,'{{BOOKMARK}}')]/../../..";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
