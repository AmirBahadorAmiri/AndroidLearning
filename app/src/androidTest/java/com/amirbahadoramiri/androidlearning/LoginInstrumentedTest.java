package com.amirbahadoramiri.androidlearning;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;

import org.junit.Test;

public class LoginInstrumentedTest {

    @Test
    public void test() {
        onView(withId(R.id.email)).perform(typeText("amir@gmail.com"));
    }
}
