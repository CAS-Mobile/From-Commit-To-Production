package ch.hsr.mge.gadgeothek.ui;

import org.junit.Ignore;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import ch.hsr.mge.gadgeothek.ui.login.LoginActivity;


public class LoginActivityRobolectricTest {

    @Ignore
    @Test
    public void testV1() {
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);

        // Act now

        // Assert something important

    }

    @Ignore
    @Test
    public void testV2() {

        ActivityController<LoginActivity> controller = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .start();
        LoginActivity activity = controller.get();


        // Act now

        // Assert something important

    }


}
