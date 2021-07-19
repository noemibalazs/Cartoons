package com.urban.androidhomework

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.urban.androidhomework.adapter.CharacterListener
import com.urban.androidhomework.adapter.CharacterVH
import com.urban.androidhomework.adapter.CharactersAdapter
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.CharacterLocation
import com.urban.androidhomework.landing.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.urban.androidhomework.test", context.packageName)
    }


    @Test
    fun testMainBusinessLogic() {
        val characterListener: CharacterListener = { name, index ->
        }

        val adapter = CharactersAdapter(characterListener)
        adapter.submitList(getCharacters())

        rule.scenario.onActivity {
            it.findViewById<RecyclerView>(R.id.rvCharacters).adapter = adapter
        }

        onView(withId(R.id.rvCharacters)).check(matches(isDisplayed()))
        onView(withId(R.id.rvCharacters)).perform(
                RecyclerViewActions.actionOnItemAtPosition<CharacterVH>(
                        0,
                        click()
                )
        )
    }

    @Test
    fun testBusinessLogic() {
        onView(withId(R.id.tvFilterBy)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.ivCalendar)).check(matches(isDisplayed()))
        onView(withId(R.id.ivDone)).check(matches(isDisplayed())).check(matches(isClickable()))
    }

    private fun getCharacters(): MutableList<CharacterData> {
        val characterList = mutableListOf<CharacterData>()
        characterList.add(CharacterData(id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", created = "2017-11-30T14:23:41.053Z", image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CharacterLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4")))
        characterList.add(CharacterData(id = 1, name = "Morty Smith", species = "Human", status = "Alive", type = "",
                gender = "Male", created = "2017-11-30T14:23:41.053Z", image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg", location =
        CharacterLocation(name = "Earth", url = "https://rickandmortyapi.com/api/location/20")))
        return characterList
    }
}