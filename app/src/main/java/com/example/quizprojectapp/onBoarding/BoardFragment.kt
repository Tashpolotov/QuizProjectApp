package com.example.quizprojectapp.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.FragmentBoardBinding
import com.example.quizprojectapp.fragment.MainFragment
import com.example.quizprojectapp.onBoarding.model.OnBoardModel
import com.example.quizprojectapp.onBoarding.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        arguments.let {
            val data = it?.getSerializable("board") as OnBoardModel
            binding.tvName.text = data.desc
            binding.tvDesc.text = data.text
            data.image?.let { it1 -> binding.img.setImageResource(it1) }
        }
        binding.btnNext.setOnClickListener {
            val fragmentManager = requireFragmentManager()
            fragmentManager.beginTransaction().replace(R.id.fr_container, MainFragment())
                .commit()
            preferences.setBoardingShow(true)
        }
    }
}