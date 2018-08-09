package io.tanners.libs;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.tanners.libs.test.IdlingResourceHelper;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Credit goes too https://pavneetsblog.wordpress.com/2017/10/13/testing-fragments-and-network-call-using-espresso/
 */
@RunWith(AndroidJUnit4.class)
public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance()
                .register(
                        IdlingResourceHelper
                                .getInstance()
                                .getIdlingResource()
                );
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance()
                .unregister(
                        IdlingResourceHelper
                                .getInstance()
                                .getIdlingResource()
                );
    }

    @Test
    public void clickForJoke_EmptyStringTest() {
        onView(
                withId(
                        R.id.findJokeBtn
                )
        ).perform(
                click()
        );

        onView(
                withId(R.id.joke_display)
        ).check(
                matches(
                        not(
                                withText("")
                        )
                )
        );
    }
}
