package com.urban.androidhomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.urban.androidhomework.R
import com.urban.androidhomework.api.model.CharacterData

typealias CharacterListener = (id: Int, index: Int) -> Unit

class CharactersAdapter(private val characterListener: CharacterListener) : ListAdapter<CharacterData, CharacterVH>(CharacterDifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterVH(view, characterListener)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.onBind(holder, getItem(position))
    }
}