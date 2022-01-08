package lib.ui.MobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type=search]";
        SEARCH_RESULT_BY_SUBSTRING_TPL =
                "xpath://div[@class='results']//li[contains(@title,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath://div[@class='header-action']//button[@type='button']";
        SEARCH_RESULT_ELEMENT = "xpath://div[@class='results']//li[@class='page-summary']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://p[@class='without-results']";
        ADD_SEARCH_ARTICLE_TO_LIST = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION =
                "xpath://[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//[contains(text(),'{TITLE}')]" +
                        "//.." +
                        "//*[contains(text(),'{DESCRIPTION}')]";

        // тот что нужен для поиска совпадения в TITLE статьи - //a[@class='title ']//h3//*[contains(text(),'Java')]
        ////a[@class='title ']//h3//strong[text()='Java']
        //a[@class='title ']//h3//strong[contains(text(),'Java')]
        SEARCH_GET_TEXT_FOR_TITLE = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_GET_TEXT_FOR_DESCRIPTION = "xpath://*[@text='{SUBSTRING}']";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
