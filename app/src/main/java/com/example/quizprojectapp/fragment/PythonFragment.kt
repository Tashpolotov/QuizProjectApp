package com.example.quizprojectapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizprojectapp.R
import com.example.quizprojectapp.adapter.QuestionsAdapter
import com.example.quizprojectapp.databinding.FragmentJavaBinding
import com.example.quizprojectapp.databinding.FragmentPythonBinding
import com.example.quizprojectapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PythonFragment : Fragment() {

    private lateinit var binding: FragmentPythonBinding
    private val adapter = QuestionsAdapter()
    private val viewModel by viewModels<QuizViewModel>()
    private var currentQuestionIndex = 0
    private var isCurrentQuestionAnswered = false
    private var correctAnswersCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPythonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(name = String())
        updateQuestionNumber()
        binding.imgBack.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fr_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initView(name: String) {
        binding.rv.adapter = adapter

        adapter.setOnAnswerClickListener(object : QuestionsAdapter.OnAnswerClickListener {
            override fun onAnswerClick(selectedIndex: Int) {
                isCurrentQuestionAnswered = true

                val currentQuestion = viewModel.programmer.value?.questionsPythone?.get(currentQuestionIndex)
                if (currentQuestion != null && currentQuestion.currentAnswer.toInt() == selectedIndex) {
                    correctAnswersCount++
                }
                updateQuestionNumber()
            }
        })

        binding.btnNext.setOnClickListener {
            val questions = viewModel.programmer.value?.questionsPythone ?: emptyList()
            if (currentQuestionIndex < questions.size) {
                if (isCurrentQuestionAnswered) {
                    currentQuestionIndex++
                    isCurrentQuestionAnswered = false // Сбросить флаг ответа
                    adapter.clearAnsweredQuestions() // Сбросить флаги ответов в адаптере
                    if (currentQuestionIndex < questions.size) {
                        adapter.submitList(listOf(questions[currentQuestionIndex]))
                        updateQuestionNumber()
                    }

                    if (currentQuestionIndex == questions.size) {
                        val fragment = ResultFragment()
                        val args = Bundle()
                        args.putInt("correctAnswersCount", correctAnswersCount)
                        args.putInt("totalQuestions", questions.size)
                        fragment.arguments = args

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fr_container, fragment)
                            .addToBackStack(null)
                            .commit()
                    }
                } else {

                }
            } else {

            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.programmer.collect { programmerModel ->
                val questions = programmerModel?.questionsPythone ?: emptyList()
                if (questions.isNotEmpty()) {
                    adapter.submitList(listOf(questions[currentQuestionIndex]))
                }
            }
        }

        viewModel.loadQuestionsPython(name)

    }
    private fun updateQuestionNumber() {
        val currentQuestionNumber = currentQuestionIndex + 1
        val questions = viewModel.programmer.value?.questionsPythone ?: emptyList()
        val questionNumberText = "$currentQuestionNumber/${questions.size}"
        binding.tvCount.text = questionNumberText
    }
}
