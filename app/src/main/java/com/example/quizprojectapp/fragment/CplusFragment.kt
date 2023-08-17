package com.example.quizprojectapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quizprojectapp.R
import com.example.quizprojectapp.adapter.QuestionsAdapter
import com.example.quizprojectapp.databinding.FragmentCplusBinding
import com.example.quizprojectapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CplusFragment : Fragment() {

    private lateinit var binding: FragmentCplusBinding
    private val adapter = QuestionsAdapter()
    private val viewModel by viewModels<QuizViewModel>()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCplusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = arguments?.getString("categoryName")
        initView(categoryName ?: "")
    }

    private fun initView(name: String) {
        binding.rv.adapter = adapter

        adapter.setOnAnswerClickListener(object : QuestionsAdapter.OnAnswerClickListener {
            override fun onAnswerClick(selectedIndex: Int) {
                // Handle answer click here if needed
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.programmer.collect { programmerModel ->
                val questions = programmerModel?.questionsCplus ?: emptyList()
                if (questions.isNotEmpty()) {
                    adapter.submitList(listOf(questions[currentQuestionIndex]))

                    binding.btnNext.setOnClickListener {
                        currentQuestionIndex++
                        adapter.clearAnsweredQuestions() // Clear answered questions
                        if (currentQuestionIndex < questions.size) {
                            adapter.submitList(listOf(questions[currentQuestionIndex]))
                        } else {
                            // All questions answered
                            // You can show a message or handle the completion here
                        }
                    }
                }
            }
        }

        viewModel.loadQuestionsCplus(name)
    }
}