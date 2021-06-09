package com.urban.androidhomework

import android.content.SharedPreferences
import android.os.Build
import com.nhaarman.mockito_kotlin.mock
import com.urban.androidhomework.preferences.PreferencesRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.NullPointerException

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class PreferencesRepositoryUnitTest {

    private lateinit var preferencesRepository: PreferencesRepository
    private val sharedPreferences: SharedPreferences = mock()
    private val index = 12

    @Before
    fun setUp() {
        preferencesRepository = PreferencesRepository(sharedPreferences)
    }

    @Test(expected = NullPointerException::class)
    fun testShouldPass() {
        preferencesRepository.index = index
        val expected = preferencesRepository.index
        assertEquals(expected, index)
    }

    @Test(expected = NullPointerException::class)
    fun testShouldFailed() {
        preferencesRepository.index = index
        val expected = preferencesRepository.index
        assertNotEquals(expected, index - 1)
    }
}