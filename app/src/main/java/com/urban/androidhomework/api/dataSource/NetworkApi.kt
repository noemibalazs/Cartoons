package com.urban.androidhomework.api.dataSource

import com.urban.androidhomework.api.model.Character
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.LocationDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("character/?")
    fun getAllCharacters(@Query("page") page: Int): Single<Character>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Single<CharacterData>

    @GET("location/{id}")
    fun getLocationDetails(@Path("id") id: Int): Single<LocationDetails>
}