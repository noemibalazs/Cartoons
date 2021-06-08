package com.urban.androidhomework.mapper

import android.util.Log
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.CharacterLocation
import com.urban.androidhomework.room.CartoonData
import com.urban.androidhomework.room.CartoonLocation
import com.urban.androidhomework.utils.Utils
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapCartoonData2CharacterData(cartoonData: CartoonData): CharacterData {
        return CharacterData(
                id = cartoonData.id,
                name = cartoonData.name,
                species = cartoonData.species,
                status = cartoonData.status,
                gender = cartoonData.gender,
                type = cartoonData.type,
                created = Utils.long2String(cartoonData.date),
                location = CharacterLocation(name = cartoonData.location.name, url = cartoonData.location.url),
                image = cartoonData.image
        )
    }

    fun mapCharacterData2CartoonData(data: CharacterData): CartoonData {
        Log.d("MAPPER", "Each date: ${Utils.string2Long(data.created)}")
        return CartoonData(
                id = data.id,
                name = data.name,
                species = data.species,
                status = data.status,
                gender = data.gender,
                type = data.type ?: "",
                image = data.image,
                location = CartoonLocation(name = data.location.name, url = data.location.url
                        ?: ""),
                date = Utils.string2Long(data.created)
        )
    }
}