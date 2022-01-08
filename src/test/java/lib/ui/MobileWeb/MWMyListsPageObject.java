package lib.ui.MobileWeb;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        //FOLDER_BY_NAME = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE = "xpath://li[@class='page-summary with-watchstar']//h3[contains(text(),'{TITLE}')]";
        //OPEN_BOOKMARK = "xpath://*[contains(@text,'{{BOOKMARK}}')]/../../..";
        REMOVE_FROM_SAVED_BUTTON = "xpath://h3[contains(text(),'{TITLE}')]//..//..//a[@aria-controls='mw-watchlink-notification']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
