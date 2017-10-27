package ch.hsr.mge.gadgeothek;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import ch.hsr.mge.gadgeothek.http.HttpProxy;
import ch.hsr.mge.gadgeothek.http.MockedGadgeothekBackend;
import ch.hsr.mge.gadgeothek.ui.RegisterActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterTest {
    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    private MockedGadgeothekBackend backend;

    @Before
    public void setUp() {
        HttpProxy httpProxy = GadgeothekTestApplication.httpProxy;
        httpProxy.clear();
        backend = new MockedGadgeothekBackend(GadgeothekTestApplication.httpProxy);
    }

    @Test
    public void registerSuccessfully() throws InterruptedException {
        backend.givenRegisterSuccessful();
        backend.givenLoginSuccessful("1234", "token");
        backend.givenEmptyLoans();
        backend.givenEmptyLoans();

        new RegisterPage()
                .registerSuccessfully("Yves", "yves.bonjour@gmail.com", 1234, "password")
                .verify()
                .gadgeothekIsDisplayed();
    }

    @Test
    public void registerFailsDuplicatedEMail() throws InterruptedException {
        backend.givenRegisterUnsuccessful();

        new RegisterPage()
                .registerUnsuccessfully("Yves", "yves.bonjour@gmail.com", 1234, "password")
                .verify()
                .passwordErrorMessageIsVisible();
    }

    @Test
    public void registerFailsServerError() throws InterruptedException {
        backend.givenRegisterServerError();

        new RegisterPage()
                .registerUnsuccessfully("Yves", "yves.bonjour@gmail.com", 1234, "password")
                .verify()
                .errorMessageIsVisible();
    }
}
