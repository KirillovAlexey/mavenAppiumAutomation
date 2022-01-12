package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            ADD_SEARCH_ARTICLE_TO_LIST,
            SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION,
            SEARCH_GET_TEXT_FOR_TITLE,
            SEARCH_GET_TEXT_FOR_DESCRIPTION;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot fond search input after clicking search init element");
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
    }

    @Step("Input words '{searchLine}' in the search field")
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find and type into search input",
                5);
    }

    @Step("Waiting results in searchList")
    public void waitForSearchResult(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementPresent(
                searchResultXpath,
                "Cannot find search result with substring" + subString
        );
    }

    @Step("Waiting button for cancel search result")
    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button", 5);
    }

    @Step("Waiting cancel search result disappear")
    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present", 5);
    }

    @Step("Click the button 'cancel search' results")
    public void clickCancelSearch() {
        this.waitForCancelButtonAppear();
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }

    @Step("Waiting and click to article with sub string search")
    public void clickByArticleWithSubString(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(
                searchResultXpath,
                "Cannot find and click search result with substring" + subString,
                10
        );
    }

    @Step("Getting count finds articles")
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);
        return this.getAmountElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting empty result search")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultSearch() {
        this.assertElementsNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }

    @Step("Making sure that the search results are there")
    public void assertThereIsResultSearch() {
        this.assertElementsNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }

    @Step("Checking the word in the title of all search articles")
    public void checkSearchWordInFindsArticles(String searchWord) {
        this.list.clear();
        this.list.addAll(driver.findElementsById(ADD_SEARCH_ARTICLE_TO_LIST));
        this.checkWordInputAllUrls(list, searchWord);
    }

    @Step("Checking the word in the title of all search articles №2?")
    public void checkFindsArticleInSearchingList() {
        this.getFindsElements(ADD_SEARCH_ARTICLE_TO_LIST);
        for (WebElement article : this.list) {
            if (Platform.getInstance().isMw()) {
                this.waitForElementPresent(
                        ADD_SEARCH_ARTICLE_TO_LIST +
                                "[@data-title='" + article.getAttribute("data-title") + "']",
                        "Cannot find this article" + article);
            } else {
                this.waitForElementPresent(
                        ADD_SEARCH_ARTICLE_TO_LIST +
                                "[@text='" + article.getText() + "']",
                        "Cannot find this article" + article);
            }
        }
    }

    @Step("Waiting title and description articles")
    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchXpath = getResultSearchElementForTitleAndDescriptions(
                title, description);
        this.waitForElementPresent(
                searchXpath,
                "Cannot find article for this " + title + " or this " + description,
                10
        );
    }

    /*TEMPLATES METHODS*/
    @Step("Getting finally searchString")
    private static String getResultSearchElement(String subString) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    private static String getResultSearchElementForTitleAndDescriptions(String title, String description) {

        return SEARCH_ARTICLE_BY_TITLE_AND_DESCRIPTION
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/
}
