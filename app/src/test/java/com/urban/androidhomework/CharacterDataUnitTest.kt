package com.urban.androidhomework

import android.os.Build
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.CharacterLocation
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class CharacterDataUnitTest {

    private val result = CharacterData(id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
            gender = "Male", created = "2017-11-30T14:23:41.053Z", image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
    CharacterLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))

    @Test
    fun testShouldPass() {
        val expected = CharacterData(id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", created = "2017-11-30T14:23:41.053Z", image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CharacterLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        assertEquals(expected, result)
    }

    @Test
    fun testShouldFailed() {
        val expected = CharacterData(id = 81, name = "Dale", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", created = "2017-11-30T14:23:41.053Z", image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CharacterLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        assertNotEquals(expected, result)
    }
}