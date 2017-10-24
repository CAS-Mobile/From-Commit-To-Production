package ch.hsr.mge.gadgeothek;


import android.support.test.espresso.Espresso;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.String.valueOf;

public class RegisterPage {

    public GadgeothekPage registerSuccessfully(
            String name,
            String email,
            int studentNumber,
            String password) {
        onView(withId(R.id.name)).perform(typeText(name));
        onView(withId(R.id.email)).perform(typeText(email));
        onView(withId(R.id.matrikelnr)).perform(typeText(valueOf(studentNumber)));
        onView(withId(R.id.password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.registerButton)).perform(click());

        return new GadgeothekPage();
    }
}
