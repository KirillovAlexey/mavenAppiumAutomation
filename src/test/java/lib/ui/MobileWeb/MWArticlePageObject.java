package lib.ui.MobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[@title='Remove this page from your watchlist']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://a[@title='Watch']";
        ////li[@id='page-actions-watch']
        //ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@text, 'GOT IT')]";
        //MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        //MY_LIST_OK_BUTTON = "xpath://*[contains(@text, 'OK')]";
        //CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc, 'Navigate up')]";
        //ADD_TO_MY_CREATED_LIST_PREVIOUS = "xpath://*[contains(@text,'{nameFolder}')]/../..";
        //ADD_NEW_FOLDER_NAME = "id:org.wikipedia:id/create_button";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
