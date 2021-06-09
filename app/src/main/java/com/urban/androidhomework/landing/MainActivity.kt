package com.urban.androidhomework.landing

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.urban.androidhomework.R
import com.urban.androidhomework.adapter.adapter.CharacterListener
import com.urban.androidhomework.adapter.adapter.CharactersAdapter
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.app.UrbanHomeWorkApp
import com.urban.androidhomework.details.CharacterDetailActivity
import com.urban.androidhomework.mapper.Mapper
import com.urban.androidhomework.preferences.PreferencesRepository
import com.urban.androidhomework.room.CartoonData
import com.urban.androidhomework.utils.*
import com.urban.androidhomework.viewModel.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    @Inject
    lateinit var mapper: Mapper

    private lateinit var calendar: Calendar

    private var dateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateFilterCondition()
    }

    private fun updateFilterCondition() {
        val sdf = SimpleDateFormat(Utils.OUTPUT_FORMAT, Locale.getDefault())
        tvDate.text = sdf.format(calendar.time)
    }

    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(characterListener)
    }

    private val characterListener: CharacterListener = { id, index ->
        preferencesRepository.index = index
        launchCharacterDetails(id)
        Log.d(TAG, "Recycle view index: $index - id: $id")
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

        calendar = Calendar.getInstance()

        setUpRV()
        initClickListeners()
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

    private fun initClickListeners() {
        clFilterLogic.setOnClickListener(object : OnTimeClickListener() {
            override fun oneTimeClick(view: View) {
                DatePickerDialog(this@MainActivity,
                        dateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        ivDone.setOnClickListener(object : OnTimeClickListener() {
            override fun oneTimeClick(view: View) {
                if (tvDate.text.isNullOrEmpty()) {
                    characterViewModel.filterConditionError.value = getString(R.string.txt_filter_condition_error_label)
                } else {
                    val date: Long = Utils.longForOutPut(tvDate.text.toString().trimEnd())
                    characterViewModel.filterCartoonsFromDB(date)
                    characterViewModel.progress.value = true
                    tvDate.text = ""
                    adapter.submitList(null)
                }
            }
        })
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
            characterViewModel.addCartoons2DB(getCartoons(it))
        })

        characterViewModel.errorEvent.observe(this, Observer {
            showErrorToastToUser(it)
        })

        characterViewModel.progress.observe(this, Observer {
            progressBar.isVisible = it
        })

        characterViewModel.filterConditionError.observe(this, Observer {
            showErrorToastToUser(it)
        })

        characterViewModel.filteredCartoons.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                Handler(Looper.getMainLooper()).postDelayed({
                    showErrorToastToUser(getString(R.string.txt_no_result_try_it_again_label))
                    characterViewModel.getAllCharacters()
                }, 500L)
            } else {
                updateUI(it)
            }
        })
    }

    private fun getCartoons(data: List<CharacterData>): MutableList<CartoonData> {
        val cartoons = mutableListOf<CartoonData>()
        data.forEach { character ->
            cartoons.add(mapper.mapCharacterData2CartoonData(character))
        }
        return cartoons
    }

    private fun updateUI(list: MutableList<CartoonData>) {
        Log.d(TAG, "updateUI()")
        val data = mutableListOf<CharacterData>()
        list.forEach { cartoon ->
            data.add(mapper.mapCartoonData2CharacterData(cartoon))
        }
        adapter.submitList(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val KEY_CHARACTER_ID = "character_id"
        private const val TAG = "MainActivity"
    }
}