package com.jobseekers.cns_assignment.mainFlow

import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jobseekers.cns_assignment.Constant
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.mainFlow.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidTest {
    @get : Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var navController: NavHostController
    private lateinit var mainActivityViewModel: MainActivityViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        mainActivityViewModel =
            Constant.getMainActivityViewModel()
        //Getting the NavController for test
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.nav_main_flow)
        }
    }


    /**
     * This test method perform scenario open app
     * Check Reports Recycler is visible
     */
    @Test
    fun isReportRecyclerVisibleTest() {
        Espresso.onView(Matchers.allOf(withId(R.id.reportsRv), ViewMatchers.isDisplayed()))
    }

    /**
     * This test method perform scenario open app
     * Check Reports
     */

    @Test
    fun searchViewIsVisibleTest(){
        onView(Matchers.allOf(withId(R.id.textField), ViewMatchers.isDisplayed()))
    }
}