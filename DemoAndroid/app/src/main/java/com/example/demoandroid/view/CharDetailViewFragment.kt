package com.example.demoandroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.demoandroid.BuildConfig
import com.example.demoandroid.common.AppUtils
import com.example.demoandroid.databinding.FragmentViewBinding
import com.example.demoandroid.viewmodel.CharViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharDetailViewFragment() : Fragment() {

    private val userViewModel by activityViewModels<CharViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewBinding.inflate(inflater, container, false)

        if (userViewModel.getSelected().value != null) {
            Glide.with(this)
                    .load(BuildConfig.BASE_URL + userViewModel.getSelected().value!!.Icon.URL)
                    .into(binding.appBarImage)

            binding.title.text =
                    AppUtils.splitCharacters(userViewModel.getSelected().value!!.Text)[0]
            binding.description.text =
                    AppUtils.splitCharacters(userViewModel.getSelected().value!!.Text.trimIndent())[1]
        }
        return binding.root
    }
}