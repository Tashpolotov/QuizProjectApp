package com.example.quizprojectapp.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizprojectapp.databinding.FragmentPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPager2Fragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterBoard = BoardAdapter(requireActivity())
        binding.viewPager222.adapter = adapterBoard
        binding.indic.attachTo(binding.viewPager222)
    }
}