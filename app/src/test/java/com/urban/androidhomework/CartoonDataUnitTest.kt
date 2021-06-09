package com.urban.androidhomework

import android.os.Build
import com.urban.androidhomework.room.CartoonData
import com.urban.androidhomework.room.CartoonLocation
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class CartoonDataUnitTest {

    private val result = CartoonData(
            id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
            gender = "Male", date = 1509494400, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
    CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4")
    )

    @Test
    fun testShouldPass() {
        val expected = CartoonData(
                id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4")
        )
        assertEquals(expected, result)
    }

    @Test
    fun testShouldFailed() {
        val expected = CartoonData(
                id = 81, name = "Crocubot", species = "Human", status = "Alive", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4")
        )
        assertNotEquals(expected, result)
    }
}