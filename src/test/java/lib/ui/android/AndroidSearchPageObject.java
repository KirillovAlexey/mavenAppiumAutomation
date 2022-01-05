package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";//"xpath://*[contains(@text, 'Search…')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        ADD_SEARCH_ARTICLE_TO_LIST = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION =
                "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='{TITLE}']" +
                        "//.." +
                        "//*[@text='{DESCRIPTION}']";
        SEARCH_GET_TEXT_FOR_TITLE = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_GET_TEXT_FOR_DESCRIPTION = "xpath://*[@text='{SUBSTRING}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
