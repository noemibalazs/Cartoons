package com.urban.androidhomework

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.nhaarman.mockito_kotlin.mock
import com.urban.androidhomework.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class LocalDataRepositoryUnitTest {

    private lateinit var cartoonDB: CartoonDB
    private lateinit var dao: CartoonDAO
    private val context: Context = mock()

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cartoonDB = Room.inMemoryDatabaseBuilder(context, CartoonDB::class.java).build()
        dao = cartoonDB.getDAO()
    }

    @Test
    @Throws(Exception::class)
    fun testInsertCartoonsShouldPass() {
        val cartoons = mutableListOf<CartoonData>()
        val cartoon = CartoonData(
                id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400L, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        cartoons.add(cartoon)
        CoroutineScope(Dispatchers.IO).launch {
            val id = dao.addCartoonList(cartoons)
            assertNotNull(id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testInsertedCartoonsSizeShouldFailed() {
        val cartoons = mutableListOf<CartoonData>()
        val cartoon = CartoonData(
                id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400L, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        cartoons.add(cartoon)
        CoroutineScope(Dispatchers.IO).launch {
            dao.addCartoonList(cartoons)
            val size = dao.getCartoons()
            assertNotEquals(size, 3)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFilterCartoonsShouldPass() {
        val filterCondition = 1509494400L
        val cartoons = mutableListOf<CartoonData>()
        val cartoon = CartoonData(
                id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400L, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        cartoons.add(cartoon)
        CoroutineScope(Dispatchers.IO).launch {
            dao.addCartoonList(cartoons)
            val filteredList = dao.filteredCartoons(filterCondition)
            assertEquals(filteredList.size, 1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFilterCartoonsShouldFail() {
        val filterCondition = 1309494400L
        val cartoons = mutableListOf<CartoonData>()
        val cartoon = CartoonData(
                id = 81, name = "Crocubot", species = "Animal", status = "Dead", type = "Robot-Crocodile hybrid",
                gender = "Male", date = 1509494400L, image = "https://rickandmortyapi.com/api/character/avatar/81.jpeg", location =
        CartoonLocation(name = "Worldender's lair", url = "https://rickandmortyapi.com/api/location/4"))
        cartoons.add(cartoon)
        CoroutineScope(Dispatchers.IO).launch {
            dao.addCartoonList(cartoons)
            val filteredList = dao.filteredCartoons(filterCondition)
            assertNotEquals(filteredList.size, 3)
        }
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        cartoonDB.close()
    }
}