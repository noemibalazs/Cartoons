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
import com.urban.androidhomework.local.LocalDataService
import com.urban.androidhomework.room.CartoonData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val networkService: NetworkService,
                                             private val localDataService: LocalDataService) : BaseCharacterViewModel() {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val mutableCharacters = MutableLiveData<List<CharacterData>>()
    val mutableCharacter = MutableLiveData<CharacterData>()
    val mutableLocationDetails = MutableLiveData<LocationDetails?>()
    val mutableCharacterLocation = MutableLiveData<CharacterLocation>()
    val filterConditionError = MutableLiveData<String>()
    val filteredCartoons = MutableLiveData<MutableList<CartoonData>>()

    var pageIndex = 1
    private val allResults = mutableListOf<CharacterData>()

    fun getAllCharacters() {
        Log.d(TAG, "getAllCharacters()")
        compositeDisposable.clear()
        val disposable = networkService.getAllCharacters(pageIndex)
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
                        if (pageIndex < 6) {
                            allResults.addAll(character.results)
                            pageIndex++
                            getAllCharacters()
                        } else {
                            mutableCharacters.value = allResults
                        }
                        Log.d(TAG, "getAllCharacters onSuccess() - pageIndex: $pageIndex")
                    }

                    override fun onError(e: Throwable) {
                        errorEvent.value = e.message ?: ERROR_MESSAGE
                        Log.e(TAG, "getAllCharacters onError() - ${e.printStackTrace()}")
                    }
                })
        compositeDisposable.add(disposable)
    }

    fun getCharacterDetails(id: Int) {
        Log.d(TAG, "getCharacterDetails()")
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
        Log.d(TAG, "getLocationDetails()")
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

    fun addCartoons2DB(list: MutableList<CartoonData>) {
        Log.d(TAG, "addCartoons2DB()")
        CoroutineScope(Dispatchers.IO).launch {
            localDataService.addCartoonList(list)
        }
    }

    fun filterCartoonsFromDB(date: Long) {
        Log.d(TAG, "filterCartoonsFromDB()")
        progress.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = localDataService.filteredCartoons(date)
            withContext(Dispatchers.Main) {
                try {
                    filteredCartoons.value = result
                    progress.value = false
                } catch (e: Exception) {
                    progress.value = false
                    errorEvent.value = e.message ?: ERROR_MESSAGE
                    Log.e(TAG, "${e.printStackTrace()}")
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val networkService: NetworkService,
                  private val localDataService: LocalDataService) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(networkService, localDataService) as T
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "An error has occurred!"
        private const val TAG = "CharacterViewModel"
    }
}