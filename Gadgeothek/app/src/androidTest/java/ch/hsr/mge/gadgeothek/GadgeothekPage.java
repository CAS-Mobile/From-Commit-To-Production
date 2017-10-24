package ch.hsr.mge.gadgeothek;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GadgeothekPage {

    public Verifications verify() {
        return new Verifications();
    }

    static class Verifications {
        public Verifications gadgeothekIsDisplayed() {
            onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
            return this;
        }
    }
}
