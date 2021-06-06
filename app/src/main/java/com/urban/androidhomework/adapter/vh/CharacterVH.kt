package com.urban.androidhomework.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.urban.androidhomework.R
import com.urban.androidhomework.adapter.adapter.CharacterListener
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.utils.OnTimeClickListener
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterVH(
        private val view: View,
        private val characterListener: CharacterListener?) : RecyclerView.ViewHolder(view) {

    fun onBind(holder: CharacterVH, data: CharacterData) {
        Glide.with(view.context)
                .load(data.image)
                .placeholder(R.drawable.wolves)
                .error(R.drawable.wolves)
                .into(view.ivCharacter)
        view.tvCharacterName.text = data.name
        view.tvCharacterStatus.text = data.status

        itemView.setOnClickListener(object : OnTimeClickListener() {
            override fun oneTimeClick(view: View) {
                val index = holder.absoluteAdapterPosition
                characterListener?.invoke(data.id, index)
            }
        })
    }
}