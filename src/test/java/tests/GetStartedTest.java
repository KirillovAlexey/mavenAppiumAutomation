package tests;

import lib.Platform;
import org.junit.Test;

public class GetStartedTest {
    @Test
    public void testPassThroughtWelcome() {
        if (Platform.getInstance().isMw() || Platform.getInstance().isAndroid()) {
            return;
        }

        //какой то код для iOS
    }
}
