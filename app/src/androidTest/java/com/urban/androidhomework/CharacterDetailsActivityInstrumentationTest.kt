package com.urban.androidhomework

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.urban.androidhomework.details.CharacterDetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CharacterDetailsActivityInstrumentationTest {

    @get:Rule
    val rule = activityScenarioRule<CharacterDetailActivity>()

    @Test
    fun testBusinessLogic() {

        rule.scenario.onActivity {
            it.findViewById<AppCompatImageView>(R.id.ivCharacterDetails).setImageResource(R.drawable.wolves)
            it.findViewById<AppCompatTextView>(R.id.tvCharacterDetailsName).text = NAME_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvCharacterDetailsStatus).text = STATUS_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvCharacterDetailsSpecies).text = SPECIES_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvCharacterDetailsGender).text = GENDER_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvLocationName).text = NAME_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvLocationType).text = TYPE_LABEL
            it.findViewById<AppCompatTextView>(R.id.tvLocationDimension).text = DIMENSIONS_LABEL
        }

        onView(withId(R.id.tvCharacterLocation)).check(matches(isDisplayed()))
        onView(withId(R.id.ivCharacterDetails)).check(matches(isDisplayed()))

        onView(withId(R.id.tvCharacterDetailsName)).check(matches(isDisplayed())).check(matches((withText(NAME_LABEL))))
        onView(withId(R.id.tvCharacterDetailsSpecies)).check(matches(isDisplayed())).check(matches((withText(SPECIES_LABEL))))
        onView(withId(R.id.tvCharacterDetailsStatus)).check(matches(isDisplayed())).check(matches((withText(STATUS_LABEL))))
        onView(withId(R.id.tvCharacterDetailsGender)).check(matches(isDisplayed())).check(matches((withText(GENDER_LABEL))))
        onView(withId(R.id.tvLocationName)).check(matches(isDisplayed())).check(matches((withText(NAME_LABEL))))
        onView(withId(R.id.tvLocationType)).check(matches(isDisplayed())).check(matches((withText(TYPE_LABEL))))
        onView(withId(R.id.nsvDetails)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tvLocationDimension)).check(matches(isDisplayed())).check(matches((withText(DIMENSIONS_LABEL))))
    }

    companion object {

        const val NAME_LABEL = "Name: Marty"
        const val STATUS_LABEL = "Status: Alive"
        const val SPECIES_LABEL = "Species: Human"
        const val GENDER_LABEL = "Gender: Male"
        const val TYPE_LABEL = "Type: Planet"
        const val DIMENSIONS_LABEL = "Dimension: Replacement"
    }
}