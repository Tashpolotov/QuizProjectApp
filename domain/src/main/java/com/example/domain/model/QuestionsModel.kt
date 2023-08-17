package com.example.domain.model

data class QuestionsModel(
    val questions:String,
    val categoryAnswer: String,
    val answer:List<String>,
    val currentAnswer:String
)
