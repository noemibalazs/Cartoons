package com.urban.androidhomework.landing

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.urban.androidhomework.R
import com.urban.androidhomework.adapter.adapter.CharacterListener
import com.urban.androidhomework.adapter.adapter.CharactersAdapter
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.app.UrbanHomeWorkApp
import com.urban.androidhomework.details.CharacterDetailActivity
import com.urban.androidhomework.preferences.PreferencesRepository
import com.urban.androidhomework.utils.SAVED_INSTANCE_KEY
import com.urban.androidhomework.utils.launchActivity
import com.urban.androidhomework.utils.showErrorToastToUser
import com.urban.androidhomework.viewModel.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(characterListener)
    }

    private val characterListener: CharacterListener = { id, index ->
        preferencesRepository.index = index
        launchCharacterDetails(id)
        Log.d(TAG, "RV index: $index - id: $id")
    }

    private fun launchCharacterDetails(id: Int) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(KEY_CHARACTER_ID, id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UrbanHomeWorkApp.instance.component.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val characters = savedInstanceState.getParcelableArrayList<CharacterData>(SAVED_INSTANCE_KEY)
            adapter.submitList(characters)
        }

        setUpRV()
        initObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val listOfCharacters = adapter.currentList
        val characters = arrayListOf<CharacterData>()
        characters.addAll(listOfCharacters)
        outState.putParcelableArrayList(SAVED_INSTANCE_KEY, characters)
        super.onSaveInstanceState(outState)
    }

    private fun setUpRV() {
        rvCharacters.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        val index = preferencesRepository.index
        rvCharacters.layoutManager?.scrollToPosition(index)
        Log.d(TAG, "onStart() - the index to scroll: $index")
    }

    private fun initObservers() {
        characterViewModel.getAllCharacters()

        characterViewModel.mutableCharacters.observe(this, Observer {
            adapter.submitList(it)
        })

        characterViewModel.errorEvent.observe(this, Observer {
            showErrorToastToUser(it)
        })

        characterViewModel.progress.observe(this, Observer {
            progressBar.isVisible = it
        })
    }

    companion object {
        const val KEY_CHARACTER_ID = "character_id"
        private const val TAG = "MainActivity"
    }
}