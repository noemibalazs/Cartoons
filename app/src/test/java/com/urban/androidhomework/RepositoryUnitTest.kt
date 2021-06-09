package com.urban.androidhomework

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urban.androidhomework.api.dataSource.NetworkApi
import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.LocationDetails
import com.urban.androidhomework.api.remoteDataSource.NetworkRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepositoryUnitTest {

    private lateinit var failure: Throwable
    private lateinit var networkRepository: NetworkRepository
    private val networkAPI: NetworkApi = mock()

    @Before
    fun setUp() {
        networkRepository = NetworkRepository(networkAPI)
        failure = Throwable("Try it again, an error has occurred.")
    }

    @Test
    fun testCharacter_Complete() {
        val response = mock<Character>()
        whenever(networkRepository.getAllCharacters(1)).thenReturn(Single.just(response))
        networkRepository.getAllCharacters(1).test().assertComplete()
        verify(networkAPI).getAllCharacters(1)
    }

    @Test
    fun testCharacter_Error() {
        whenever(networkRepository.getAllCharacters(1)).thenReturn(Single.error(failure))
        networkRepository.getAllCharacters(1).test().assertError(failure)
        verify(networkAPI).getAllCharacters(1)
    }

    @Test
    fun testSingleCharacter_Complete() {
        val response = mock<CharacterData>()
        whenever(networkRepository.getCharacter(1)).thenReturn(Single.just(response))
        networkRepository.getCharacter(1).test().assertComplete()
        verify(networkAPI).getCharacter(1)
    }

    @Test
    fun testSingleCharacter_Error() {
        whenever(networkRepository.getCharacter(1)).thenReturn(Single.error(failure))
        networkRepository.getCharacter(1).test().assertError(failure)
        verify(networkAPI).getCharacter(1)
    }

    @Test
    fun testLocationDetails_Complete() {
        val response = mock<LocationDetails>()
        whenever(networkRepository.getLocationDetails(1)).thenReturn(Single.just(response))
        networkRepository.getLocationDetails(1).test().assertComplete()
        verify(networkAPI).getLocationDetails(1)
    }

    @Test
    fun testLocationDetails_Error() {
        whenever(networkRepository.getLocationDetails(1)).thenReturn(Single.error(failure))
        networkRepository.getLocationDetails(1).test().assertError(failure)
        verify(networkAPI).getLocationDetails(1)
    }
}