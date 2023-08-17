package com.example.domain.repository

import com.example.domain.model.ProgrammerModel
import com.example.domain.model.QuestionsModel

interface QuizRepository {

    fun getProgrammer(id:String) : List<ProgrammerModel>

    fun getQuestionsCplus(name:String): List<QuestionsModel>

    fun getQuestionsJava(name:String): List<QuestionsModel>

    fun getQuestionsPython(name:String): List<QuestionsModel>
}