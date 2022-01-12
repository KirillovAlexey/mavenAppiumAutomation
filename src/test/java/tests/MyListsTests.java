package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestsCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestsCase {

    private static final String nameFolder = "Learning Programming";
    private static final String login = "TestMobileWebVersion";
    private static final String password = "!@#qweASD";


    @Test
    @Features(value =
            {
                    @Feature(value = "Article"),
                    @Feature(value = "Search"),
                    @Feature(value = "Auth"),
                    @Feature(value = "Navigation"),
                    @Feature(value = "Favorite bookmarks")})
    @DisplayName("Checking saved articles to favorites bookmarks")
    @Description("Saving an article to favorites and checking if it has been added to the bookmarks list")
    @Severity(value = SeverityLevel.CRITICAL)
    @Step("Starting testSaveArticleToMyList")
    public void testSaveArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(nameFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle());
        }

        //articlePageObject.addArticlesToMySaved();

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameFolder);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
        myListsPageObject.waitForArticleToDisappearByTitle(article_title);
    }

    //refactoringEX5
    @Test
    @Features(value =
            {
                    @Feature(value = "Article"),
                    @Feature(value = "Search"),
                    @Feature(value = "Auth"),
                    @Feature(value = "Navigation"),
                    @Feature(value = "Favorite bookmarks")})
    @DisplayName("Checking removed saved articles from favorites bookmarks")
    @Description("saving two articles to favorites and remove one of them from the bookmarks list")
    @Severity(value = SeverityLevel.CRITICAL)
    @Step("Starting testAddArticleAndRemoveAnotherArticleFromBookmarks")
    public void testAddArticleAndRemoveAnotherArticleFromBookmarksEx5() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(nameFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle());
        }
        //articlePageObject.addFirstArticleToMyList(nameFolder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubString("Appium");

        String article_title_second = articlePageObject.getArticleTitle();


        articlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToCreatedFolder(nameFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameFolder);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
        if (Platform.getInstance().isMw()) {
            myListsPageObject.checkOutIsFlagUnwatchToArticle("Appium");
        }
        myListsPageObject.clickByBookMark(article_title_second);
        //myListsPageObject.openFolderByName(nameFolder);

        article_title = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title",
                "Appium",
                article_title);
    }
}
