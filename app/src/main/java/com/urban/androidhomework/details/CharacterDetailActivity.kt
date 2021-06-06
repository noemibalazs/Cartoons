package com.urban.androidhomework.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.urban.androidhomework.R
import com.urban.androidhomework.app.UrbanHomeWorkApp
import com.urban.androidhomework.landing.MainActivity
import com.urban.androidhomework.viewModel.CharacterViewModel
import javax.inject.Inject

class CharacterDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UrbanHomeWorkApp.instance.component.inject(this)
        setContentView(R.layout.activity_character_detail)

        val id = intent?.getIntExtra(MainActivity.KEY_CHARACTER_ID, 0)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.flContainer, CharacterFragment.newInstance(id
                    ?: 0)).commit()
        }
    }
}