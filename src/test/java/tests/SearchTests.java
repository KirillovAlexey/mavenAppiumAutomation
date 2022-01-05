package tests;

import lib.CoreTestsCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestsCase {
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        searchPageObject.typeSearchLine(searchLine);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "zxcasdasdqwe";
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    public void testSearchArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonDisappear();
    }

    //EX3
    @Test
    public void testCancelSearchEx3() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchLine = "Java";
        searchPageObject.typeSearchLine(searchLine);
        int countArticles = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Articles less that 1",
                countArticles > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.typeSearchLine("Search…");
    }

    @Test
    public void testSearchArticleByTitleAndDescription() {
        String searchWord = "Java";
        String title = "Java (programming language)";
        String description = "Object-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchWord);
        //searchPageObject.checkSearchWordInFindsArticles(searchWord);
        searchPageObject.waitForElementByTitleAndDescription(
                title,
                description);
        searchPageObject.checkSearchWordInFindsArticles(searchWord);
        int countArticles = searchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "Articles less that 3",
                countArticles >= 3);
    }
}
