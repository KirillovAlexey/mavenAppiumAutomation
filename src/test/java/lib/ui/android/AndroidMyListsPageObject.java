package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE = "xpath://*[contains(@text, '{TITLE}')]";
        OPEN_BOOKMARK = "xpath://*[contains(@text,'{{BOOKMARK}}')]/../../..";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
