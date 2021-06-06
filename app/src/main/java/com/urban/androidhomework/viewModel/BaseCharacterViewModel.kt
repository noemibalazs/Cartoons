package com.urban.androidhomework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseCharacterViewModel : ViewModel() {

    protected abstract val compositeDisposable: CompositeDisposable
    val progress = MutableLiveData<Boolean>()
    val errorEvent = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}