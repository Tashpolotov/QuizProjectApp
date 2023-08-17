package com.example.data.repository

import com.example.data.R
import com.example.domain.model.ProgrammerModel
import com.example.domain.model.QuestionsModel
import com.example.domain.repository.QuizRepository



class QuizRepositoryMock:QuizRepository {
    override fun getProgrammer(id: String): List<ProgrammerModel> {
        return listOf(
            ProgrammerModel("1", R.drawable.c, "C++"),
            ProgrammerModel("2", R.drawable.java, "Java"),
            ProgrammerModel("3", R.drawable.python, "Python"),
        )

    }

    override fun getQuestionsCplus(name: String): List<QuestionsModel> {
        return listOf(
            QuestionsModel(
            "ty kto", "C++", listOf("aza", "di", "beka", "aa"), "3"),
            QuestionsModel(
                "da", "C++", listOf("danka", "apa", "ata", "no"), "2"),
            QuestionsModel(
                "ty kto", "C++", listOf("ya", "on", "ty", "ja"), "3"),

            )
    }

    override fun getQuestionsJava(name: String): List<QuestionsModel> {
        TODO("Not yet implemented")
    }

    override fun getQuestionsPython(name: String): List<QuestionsModel> {
        TODO("Not yet implemented")
    }
}