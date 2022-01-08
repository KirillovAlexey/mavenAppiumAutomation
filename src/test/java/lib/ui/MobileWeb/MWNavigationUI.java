package lib.ui.MobileWeb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://a[@class='menu__item--unStar']";
        OPEN_NAVIGATION = "xpath://label[@title='Open main menu']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
