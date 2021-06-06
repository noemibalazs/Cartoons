package com.urban.androidhomework.di

import com.urban.androidhomework.api.dataSource.NetworkApi
import com.urban.androidhomework.api.remoteDataSource.NetworkRepository
import com.urban.androidhomework.api.remoteDataSource.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(networkApi: NetworkApi): NetworkService = NetworkRepository(networkApi)
}