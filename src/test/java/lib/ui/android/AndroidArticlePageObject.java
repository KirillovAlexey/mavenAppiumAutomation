package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@text, 'GOT IT')]";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[contains(@text, 'OK')]";
        CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc, 'Navigate up')]";
        ADD_TO_MY_CREATED_LIST_PREVIOUS = "xpath://*[contains(@text,'{nameFolder}')]/../..";
        ADD_NEW_FOLDER_NAME = "id:org.wikipedia:id/create_button";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}
