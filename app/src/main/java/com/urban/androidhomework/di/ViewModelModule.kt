package com.urban.androidhomework.di

import com.urban.androidhomework.api.remoteDataSource.NetworkService
import com.urban.androidhomework.viewModel.CharacterViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCharacterViewModel(networkService: NetworkService) = CharacterViewModel.Factory(networkService)
}