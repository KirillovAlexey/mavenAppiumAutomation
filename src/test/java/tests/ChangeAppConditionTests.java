package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestsCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestsCase {
    @Test
    @Features(value = {@Feature(value = "ChangeAppConditions"), @Feature(value = "Article")})
    @DisplayName("Default rotate by PORTRAIT")
    @Description("Opening a \"Java (programming language)\" article and rotate  to Portrait orientation default")
    @Step("Starting testRotationDefault")
    @Severity(value = SeverityLevel.NORMAL)
    public void testRotationDefault() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation);

        this.rotateScreenPortrait();
        String titleAfterSecondRotate = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                titleAfterRotation,
                titleAfterSecondRotate);
    }

    @Test
    @Features(value = {@Feature(value = "ChangeAppConditions"), @Feature(value = "Article")})
    @DisplayName("Checking the title of the article after the application is removed from the background")
    @Description("Opening a \"Java (programming language)\" article after recover from background")
    @Step("Starting testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
