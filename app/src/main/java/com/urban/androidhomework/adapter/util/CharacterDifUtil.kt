package com.urban.androidhomework.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.urban.androidhomework.api.model.CharacterData

class CharacterDifUtil : DiffUtil.ItemCallback<CharacterData>() {

    override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem == newItem
    }
}