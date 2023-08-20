package com.example.quizprojectapp.onBoarding

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quizprojectapp.R
import com.example.quizprojectapp.onBoarding.model.OnBoardModel
import dagger.hilt.android.qualifiers.ApplicationContext

class BoardAdapter (@ApplicationContext fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val listBoarding = arrayOf(
        OnBoardModel(
            "Добро пожаловать в мир программирования",
            R.drawable.java_onboard,
            "Изучайте Java, Python и C++ вместе с нами, развивайте навыки программирования и создавайте удивительные проекты"
        ),
        OnBoardModel(
            "Прокачайте свой мозг с викторинами!",
            R.drawable.cplust_onboard,
            "Погрузитесь в виртуальное соревнование по языкам программирования. Ответьте на вопросы, докажите свои навыки и станьте экспертом!",
        ),
        OnBoardModel(
            "Готовьтесь к собеседованиям с легкостью",
            R.drawable.python_onboard,
            "С нашим приложением вы сможете подготовиться к собеседованиям на позиции программиста. Мы предоставляем вам набор вопросов и ответов для тренировки, чтобы вы чувствовали себя уверенно на собеседованиях",
        )
    )
    override fun getItemCount(): Int = listBoarding.size

    override fun createFragment(position: Int): Fragment {
        val data = bundleOf("board" to listBoarding[position])
        val fragment = BoardFragment()
        fragment.arguments = data
        return fragment
    }
}