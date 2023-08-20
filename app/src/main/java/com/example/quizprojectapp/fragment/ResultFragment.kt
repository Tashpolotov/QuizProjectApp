package com.example.quizprojectapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.FragmentResultBinding
import com.example.quizprojectapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val viewModel by viewModels<QuizViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val correctAnswersCount = arguments?.getInt("correctAnswersCount", 0) ?: 0
        val totalQuestions = arguments?.getInt("totalQuestions", 0) ?: 0

        Log.d(
            "QuizApp",
            "ResultFragment: Received correctAnswersCount=$correctAnswersCount, totalQuestions=$totalQuestions"
        )
        val resultText = "$correctAnswersCount/$totalQuestions"
        binding.tvResult.text = resultText

        binding.btnMenu.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fr_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }



        binding.btnRestart.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fr_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}