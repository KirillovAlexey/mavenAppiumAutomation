package tests;

import lib.CoreTestsCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestsCase {

    private static final String nameFolder = "Learning Programming";
    private static final String login= "TestMobileWebVersion";
    private static final String password= "!@#qweASD";


    @Test
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

            assertEquals(
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
    }

    //refactoringEX5
    @Test
    public void testAddArticleAndRemoveAnotherArticleFromBookmarksEx5() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(nameFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        String nameFolder = "Learning Programming";
        articlePageObject.addFirstArticleToMyList(nameFolder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubString("Appium");

        String article_title_second = articlePageObject.getArticleTitle();
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToCreatedFolder(nameFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(nameFolder);
        myListsPageObject.swipeByArticleToDelete(article_title);
        myListsPageObject.clickByBookMark(article_title_second);

        article_title = articlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                "Appium",
                article_title);
    }
}
