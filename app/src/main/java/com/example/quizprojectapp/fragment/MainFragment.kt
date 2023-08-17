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
    }

    private fun onClick(model: ProgrammerModel) {
        val bundle = Bundle().apply {
            putString("categoryName", model.name) // Передаем имя категории
        }

        when (model.id) {
            "1" -> findNavController().navigate(R.id.action_mainFragment_to_cplusFragment, bundle)
            "2" -> findNavController().navigate(R.id.action_mainFragment_to_javaFragment, bundle)
            "3" -> findNavController().navigate(R.id.action_mainFragment_to_pythonFragment, bundle)
        }
    }
}