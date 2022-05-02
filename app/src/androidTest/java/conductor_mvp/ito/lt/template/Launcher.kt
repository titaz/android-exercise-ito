package conductor_mvp.ito.lt.template


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import it.ito.mycooking.R
import lt.ito.mycooking.launcher.LauncherActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityFirstRunTest {

    @get:Rule
    val activityRule = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun hasMainName() {
        onView(withId(R.id.launcherTitleView)).check(matches(isDisplayed()))
    }

}