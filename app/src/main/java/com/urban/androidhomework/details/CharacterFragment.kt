package com.urban.androidhomework.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.urban.androidhomework.R
import com.urban.androidhomework.api.model.CharacterData
import com.urban.androidhomework.api.model.CharacterLocation
import com.urban.androidhomework.api.model.LocationDetails
import com.urban.androidhomework.app.UrbanHomeWorkApp
import com.urban.androidhomework.utils.showErrorToastToUser
import com.urban.androidhomework.viewModel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character.*
import java.util.regex.Pattern
import javax.inject.Inject

class CharacterFragment : Fragment() {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        UrbanHomeWorkApp.instance.component.inject(this)
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val id = it.getInt(CHARACTER_ID)
            characterViewModel.getCharacterDetails(id)
            Log.d(TAG, "Id: $id")
        }

        initObservers()
    }

    private fun initObservers() {
        characterViewModel.mutableCharacter.observe(viewLifecycleOwner, Observer {
            populateUI(it)
        })

        characterViewModel.errorEvent.observe(viewLifecycleOwner, Observer {
            activity?.showErrorToastToUser(it)
        })

        characterViewModel.progress.observe(viewLifecycleOwner, Observer {
            progressBarDetails.isVisible = it
        })

        characterViewModel.mutableCharacterLocation.observe(viewLifecycleOwner, Observer {
            if (!it.url.isNullOrEmpty()) {
                characterViewModel.getLocationDetails(getLocationId(it.url))
            } else {
                tvLocationName.text = getString(R.string.txt_name_label, it.name)
            }
        })

        characterViewModel.mutableLocationDetails.observe(viewLifecycleOwner, Observer {
            it?.let { populateLocationDetails(it) }
        })
    }

    private fun populateUI(data: CharacterData) {
        tvCharacterDetailsName.text = getString(R.string.txt_name_label, data.name)
        tvCharacterDetailsStatus.text = getString(R.string.txt_status_label, data.status)
        tvCharacterDetailsSpecies.text = getString(R.string.txt_species_label, data.species)
        tvCharacterDetailsGender.text = getString(R.string.txt_gender_label, data.gender)

        Glide.with(requireContext()).load(data.image)
                .placeholder(R.drawable.wolves).error(R.drawable.wolves).into(ivCharacterDetails)
    }

    private fun getLocationId(data: String): Int {
        val regex = "https://rickandmortyapi.com/api/location/"
        return data.substring(regex.length).toInt()
    }

    private fun populateLocationDetails(locationDetails: LocationDetails) {
        tvLocationName.text = getString(R.string.txt_name_label, locationDetails.name)
        tvLocationType.text = getString(R.string.txt_type_label, locationDetails.type)
        tvLocationDimension.text = getString(R.string.txt_dimension_label, locationDetails.dimension)
    }

    companion object {

        fun newInstance(id: Int): CharacterFragment {
            val fragment = CharacterFragment()
            fragment.arguments = Bundle().apply {
                putInt(CHARACTER_ID, id)
            }
            return fragment
        }

        private const val CHARACTER_ID = "character_id"
        private const val TAG = "CharacterFragment"
    }
}