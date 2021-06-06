package com.urban.androidhomework.api.remoteDataSource

import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.LocationDetails
import io.reactivex.Single

interface NetworkService {
    fun getAllCharacters(): Single<Character>

    fun getCharacter(id: Int): Single<CharacterData>

    fun getLocationDetails(id: Int): Single<LocationDetails>
}