package com.example.quizprojectapp.viewmodel

import com.example.domain.model.ProgrammerModel
import com.example.domain.model.QuestionsModel

data class State (
    val programmer : List<ProgrammerModel> = emptyList(),
    val questionsCplus : List<QuestionsModel> = emptyList(),
    val questionsJava: List<QuestionsModel> = emptyList(),
    val questionsPythone : List<QuestionsModel> = emptyList()
        )