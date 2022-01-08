package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME,
            ARTICLE_BY_TITLE,
            OPEN_BOOKMARK,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameFolder) {
        String folderNameXpath = getFolderXpathByName(nameFolder);
        this.waitForElementAndClick(
                folderNameXpath,
                "Cannot find folder by name" + nameFolder,
                5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    articleTitleXpath,
                    "Cannot find save article" + articleTitle);
            if (Platform.getInstance().isIOS()) {
                this.clickElementAndToRightUpperCorner(articleTitleXpath,
                        "Cannot find save article" + articleTitle);
            }
            this.waitForArticleToDisappearByTitle(articleTitle);
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(
                    removeLocator,
                    "Cannot click button to remove Article from save",
                    10
            );
        }
        if (Platform.getInstance().isIOS()) {
            this.clickElementAndToRightUpperCorner(articleTitleXpath,
                    "Cannot find saved article");
        }
        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementNotPresent(
                articleTitleXpath,
                "Saved article still present with title" + articleTitle,
                15);
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(
                articleTitleXpath,
                "Cannot find saved article by title '" + articleTitle + "'",
                15);
    }

    public void clickByBookMark(String bookmark) {
        this.waitForArticleToAppearByTitle(bookmark);
        String bookmarkThis = getSavedArticleXpathByTitle(bookmark);
        this.waitForElementAndClick(
                bookmarkThis,
                "Cannot click by bookmark" + bookmark,
                5);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String nameFolder) {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", nameFolder);
    }

    private static String getOpenBookmarkXpathByName(String bookmark) {
        return OPEN_BOOKMARK.replace("{BOOKMARK}", bookmark);
    }

    private static String getSavedArticleXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }
    /*TEMPLATES METHODS*/
}
