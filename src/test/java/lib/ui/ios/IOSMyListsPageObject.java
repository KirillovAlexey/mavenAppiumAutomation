package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {

    //Переписать локаторы для iOS
    static {
        ARTICLE_BY_TITLE = "xpath://*[contains(@text, '{TITLE}')]";
        OPEN_BOOKMARK = "xpath://*[contains(@text,'{{BOOKMARK}}')]/../../..";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
