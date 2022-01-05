package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {

        //Переписать локаторы для iOS
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Add to reading list')]";
        CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc, 'Navigate up')]";
        ADD_TO_MY_CREATED_LIST_PREVIOUS = "xpath://*[contains(@text,'{nameFolder}')]/../..";
        ADD_NEW_FOLDER_NAME = "id:org.wikipedia:id/create_button";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
