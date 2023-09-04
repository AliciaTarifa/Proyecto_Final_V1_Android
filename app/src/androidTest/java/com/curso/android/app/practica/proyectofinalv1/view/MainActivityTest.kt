package com.curso.android.app.practica.proyectofinalv1.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.curso.android.app.practica.proyectofinalv1.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun buttonCompareDisabled() {
        Espresso.onView(
            ViewMatchers.withId(R.id.buttonCompare)
        ).check(
            matches(isNotEnabled())
        )
    }

    @Test
    fun buttonCompareEnableAfterTextChange() {
        val initialText = "Mi Texto"
        Espresso.onView(
            ViewMatchers.withId(R.id.editText1)).perform(typeText(initialText), closeSoftKeyboard()
        ).check(
            matches(isEnabled())
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.buttonCompare)
            ).perform(
                click()
            )
        Espresso.onView(
            ViewMatchers.withId(R.id.resultText)
        ).check(
            matches(withText("Resultado: Los textos son diferentes."))
        )
    }
}


