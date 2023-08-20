package com.example.quizprojectapp.settingfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.FragmentSettingBinding
import com.example.quizprojectapp.fragment.MainFragment
import com.example.quizprojectapp.fragment.ResultFragment
import kotlinx.coroutines.flow.collect

class SettingFragment : Fragment() {

    private lateinit var binding:FragmentSettingBinding
    private val viewModel: SettingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fr_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.music.collect{
                when(it) {
                    "yes"-> {
                        binding.imgYesSound.visibility = View.VISIBLE
                        binding.imgNoSound.visibility = View.INVISIBLE
                    }
                    "off" -> {
                        binding.imgNoSound.visibility = View.VISIBLE
                        binding.imgYesSound.visibility = View.INVISIBLE
                    }
                }
            }
        }
        binding.imgYesSound.setOnClickListener {
            viewModel.loadMusic("off")
        }
        binding.imgNoSound.setOnClickListener {
            viewModel.loadMusic("yes")
        }
    }

}