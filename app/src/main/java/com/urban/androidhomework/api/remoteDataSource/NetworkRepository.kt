package com.urban.androidhomework.api.remoteDataSource

import com.urban.androidhomework.api.dataSource.NetworkApi
import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.LocationDetails
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val networkApi: NetworkApi) : NetworkService {

    override fun getAllCharacters(page:Int): Single<Character> {
        return networkApi.getAllCharacters(page)
    }

    override fun getCharacter(id: Int): Single<CharacterData> {
        return networkApi.getCharacter(id)
    }

    override fun getLocationDetails(id: Int): Single<LocationDetails> {
        return networkApi.getLocationDetails(id)
    }
}