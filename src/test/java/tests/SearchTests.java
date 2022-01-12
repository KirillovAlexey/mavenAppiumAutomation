package tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestsCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestsCase {
    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("Getting result to search and check not empty list")
    @Description("Input search article and check not empty search results")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(searchLine);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("Getting result to search and check to empty list")
    @Description("Input search article and check empty search results")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "zxcasdasdqwe";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("Getting search article into search lists")
    @Description("Input search article and check to result search")
    @Step("Starting testSearchArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("Cancel searching and clean fields to search after get search results")
    @Description("Input search article and cancel clean fields to search")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonDisappear();
    }

    //EX3
    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("Cancel searching and clean fields after getting results articles more 1")
    @Description("Input search article and check amount articles more 1. After clean searching fields")
    @Step("Starting testCancelSearchEx3")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchEx3() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Java";
        searchPageObject.typeSearchLine(searchLine);
        int countArticles = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "Articles less that 1",
                countArticles > 1);
        searchPageObject.clickCancelSearch();
        if (driver instanceof AppiumDriver) {
            searchPageObject.typeSearchLine("Search…");
        } else {
            searchPageObject.waitForCancelButtonDisappear();
        }
    }

    @Test
    @Features(value = { @Feature(value = "Search")})
    @DisplayName("checking for an article with a title and description. Checking the number of articles more than 2")
    @Description("Input search article and check availability title and description. After check amount articles more that 2")
    @Step("Starting testSearchArticleByTitleAndDescription")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchArticleByTitleAndDescription() {
        String searchWord = "Java";
        String title = "ava (programming language)";
        String description = "bject-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchWord);
        searchPageObject.waitForElementByTitleAndDescription(
                title,
                description);
        searchPageObject.checkSearchWordInFindsArticles(searchWord);
        int countArticles = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "Articles less that 3",
                countArticles >= 3);
        //searchPageObject.getArticlesToTheSearchResults(){ }
        searchPageObject.checkFindsArticleInSearchingList();
    }
}
