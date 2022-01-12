package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestsCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("test for articles")
public class ArticleTests extends CoreTestsCase {

    @Test
    @Features(value = {@Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Swipe article to the footer")
    @Description("Opening an article and swiping it to the footer")
    @Step("Starting testSwipeArticle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Compare article title with expected one")
    @Description("Opening a \"Java (programming language)\" article and checking its title with the expected result")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();

        //articlePageObject.takeScreenshot("articlePage");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Checking a word in a search set")
    @Description("Search for articles and check the search word in each result found")
    @Step("Starting testCheckWordIntoEachFindArticle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckWordIntoEachFindArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchWord = "Java";
        searchPageObject.typeSearchLine(searchWord);
        searchPageObject.checkSearchWordInFindsArticles(searchWord);
    }

    //refactoringEX6
    @Test
    @Features(value = {@Feature(value = "Article"), @Feature(value = "Search")})
    @DisplayName("Checking the article title without waiting for the final download")
    @Description("Opening an article and comparing the title without waiting for the final download")
    @Step("Starting testCheckTitleWithoutWaitingDownloadPage")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckTitleEx6() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        String searchNameArticle = "ava (programming language)";
        searchPageObject.clickByArticleWithSubString(searchNameArticle);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.getArticleTitleWithoutTimeout();
    }
}
