package com.urban.androidhomework.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.CharacterLocation
import com.urban.androidhomework.api.model.LocationDetails
import com.urban.androidhomework.api.remoteDataSource.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val networkService: NetworkService) : BaseCharacterViewModel() {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val mutableCharacters = MutableLiveData<List<CharacterData>>()
    val mutableCharacter = MutableLiveData<CharacterData>()
    val mutableLocationDetails = MutableLiveData<LocationDetails?>()
    val mutableCharacterLocation = MutableLiveData<CharacterLocation>()

    fun getAllCharacters() {
        compositeDisposable.clear()

        val disposable = networkService.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableSingleObserver<Character>() {
                    override fun onSuccess(character: Character) {
                        mutableCharacters.value = character.results
                        Log.d(TAG, "getAllCharacters onSuccess() - size: ${character.results.size}")
                    }

                    override fun onError(e: Throwable) {
                        errorEvent.value = e.message ?: ERROR_MESSAGE
                        Log.e(TAG, "getAllCharacters onError() - ${e.printStackTrace()}")
                    }
                })
        compositeDisposable.add(disposable)
    }

    fun getCharacterDetails(id: Int) {
        compositeDisposable.clear()
        val disposable = networkService.getCharacter(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableSingleObserver<CharacterData>() {
                    override fun onSuccess(data: CharacterData) {
                        mutableCharacter.value = data
                        mutableCharacterLocation.value = data.location
                        Log.d(TAG, "getCharacterDetails onSuccess() - data: $data")
                    }

                    override fun onError(e: Throwable) {
                        errorEvent.value = e.message ?: ERROR_MESSAGE
                        Log.e(TAG, "getCharacterDetails onError() - ${e.printStackTrace()}")
                    }

                })
        compositeDisposable.add(disposable)
    }

    fun getLocationDetails(id: Int) {
        compositeDisposable.clear()
        val disposable = networkService.getLocationDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableSingleObserver<LocationDetails>() {
                    override fun onSuccess(locationDetails: LocationDetails) {
                        mutableLocationDetails.value = locationDetails
                        Log.d(TAG, "getLocationDetails onSuccess() - locationDetails: $locationDetails")
                    }

                    override fun onError(e: Throwable) {
                        errorEvent.value = e.message ?: ERROR_MESSAGE
                        Log.e(TAG, "getLocationDetails onError() - ${e.printStackTrace()}")
                    }
                })
        compositeDisposable.add(disposable)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val networkService: NetworkService) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(networkService) as T
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "An error has occurred!"
        private const val TAG = "CharacterViewModel"
    }
}