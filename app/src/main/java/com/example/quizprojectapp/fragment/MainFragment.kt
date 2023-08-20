package com.example.quizprojectapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.ProgrammerModel
import com.example.quizprojectapp.R
import com.example.quizprojectapp.adapter.MainAdapter
import com.example.quizprojectapp.databinding.FragmentMainBinding
import com.example.quizprojectapp.settingfragment.SettingFragment
import com.example.quizprojectapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val adapter = MainAdapter(this::onClick)
    private val viewModel by viewModels<QuizViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(id = String())
    }

    private fun initView(id: String) {
        binding.rv.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.programmer.collect {
                adapter.submitList(it.programmer)
            }
        }
        viewModel.loadProgrammer(id)
        binding.imgSetting.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fr_container, SettingFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun onClick(model: ProgrammerModel) {

            when (model.name) {
                "Python" ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, PythonFragment())
                        .addToBackStack(null)
                        .commit()
                "Java" ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, JavaFragment())
                        .addToBackStack(null)
                        .commit()
                "C++" ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fr_container, CplusFragment())
                        .addToBackStack(null)
                        .commit()

            }
    }
}