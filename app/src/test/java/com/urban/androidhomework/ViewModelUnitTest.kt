package com.urban.androidhomework

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.LocationDetails
import com.urban.androidhomework.api.remoteDataSource.NetworkService
import com.urban.androidhomework.local.LocalDataService
import com.urban.androidhomework.viewModel.CharacterViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.lang.NullPointerException

class ViewModelUnitTest {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var failure: Throwable
    private val networkService: NetworkService = mock()
    private val localDataService: LocalDataService = mock()
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<String> = mock()
    private val characterListObserver: Observer<List<CharacterData>> = mock()
    private val characterObserver: Observer<CharacterData> = mock()
    private val locationDetailsObserver: Observer<LocationDetails?> = mock()

    @Before
    fun setUp() {
        characterViewModel = CharacterViewModel(networkService, localDataService)
        failure = Throwable("An error has occurred, try it again!")
    }

    @Test(expected = NullPointerException::class)
    fun testGetAllCharacters_Success() {
        val response = mock<Character>()
        whenever(networkService.getAllCharacters(1)).thenReturn(Single.just(response))

        characterViewModel.getAllCharacters()
        characterViewModel.mutableCharacters.observeForever(characterListObserver)

        val characters = mutableListOf<CharacterData>()
        characters.addAll(response.results)

        verify(progressObserver).onChanged(true)
        verify(characterListObserver).onChanged(characters)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testGetAllCharacters_Error() {
        whenever(networkService.getAllCharacters(1)).thenReturn(Single.error(failure))

        characterViewModel.getAllCharacters()
        characterViewModel.mutableCharacters.observeForever(characterListObserver)

        val captor = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(characterListObserver).onChanged(null)
        verify(progressObserver).onChanged(false)
        verify(errorObserver).onChanged(captor.capture())
    }

    @Test(expected = NullPointerException::class)
    fun testCharacter_Success() {
        val response = mock<CharacterData>()
        whenever(networkService.getCharacter(1)).thenReturn(Single.just(response))

        characterViewModel.getCharacterDetails(1)
        characterViewModel.mutableCharacter.observeForever(characterObserver)

        verify(progressObserver).onChanged(true)
        verify(characterObserver).onChanged(response)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testCharacter_Error() {
        whenever(networkService.getCharacter(1)).thenReturn(Single.error(failure))

        characterViewModel.getCharacterDetails(1)
        characterViewModel.mutableCharacter.observeForever(characterObserver)

        val captor = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(characterObserver).onChanged(null)
        verify(progressObserver).onChanged(false)
        verify(errorObserver).onChanged(captor.capture())
    }

    @Test(expected = NullPointerException::class)
    fun testLocationDetails_Success() {
        val response = mock<LocationDetails>()
        whenever(networkService.getLocationDetails(1)).thenReturn(Single.just(response))

        characterViewModel.getLocationDetails(1)
        characterViewModel.mutableLocationDetails.observeForever(locationDetailsObserver)

        verify(progressObserver).onChanged(true)
        verify(locationDetailsObserver).onChanged(response)
        verify(progressObserver).onChanged(false)
    }

    @Test(expected = NullPointerException::class)
    fun testLocationDetails_Error() {
        whenever(networkService.getLocationDetails(1)).thenReturn(Single.error(failure))

        characterViewModel.getLocationDetails(1)
        characterViewModel.mutableLocationDetails.observeForever(locationDetailsObserver)

        val captor = argumentCaptor<String>()
        verify(progressObserver).onChanged(true)
        verify(locationDetailsObserver).onChanged(null)
        verify(progressObserver).onChanged(false)
        verify(errorObserver).onChanged(captor.capture())
    }
}