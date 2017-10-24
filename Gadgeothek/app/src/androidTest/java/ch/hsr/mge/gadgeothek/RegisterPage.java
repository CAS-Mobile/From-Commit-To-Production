package ch.hsr.mge.gadgeothek;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.String.valueOf;

public class RegisterPage {

    public GadgeothekPage registerSuccessfully(
            String name,
            String email,
            int studentNumber,
            String password) {
        enterRegistrationInformation(name, email, studentNumber, password);

        return new GadgeothekPage();
    }

    public RegisterPage registerUnsuccessfully(
            String name,
            String email,
            int studentNumber,
            String password) {
        enterRegistrationInformation(name, email, studentNumber, password);
        return this;
    }

    private void enterRegistrationInformation(String name, String email, int studentNumber, String password) {
        onView(withId(R.id.name)).perform(typeText(name));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.matrikelnr)).perform(typeText(valueOf(studentNumber)));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.registerButton)).perform(click());
    }

    public Verifications verify() {
        return new Verifications();
    }

    static class Verifications {
        public void errorMessageIsVisible() {
            onView(withId(android.support.design.R.id.snackbar_text)).check(matches(isDisplayed()));
        }
    }
}
