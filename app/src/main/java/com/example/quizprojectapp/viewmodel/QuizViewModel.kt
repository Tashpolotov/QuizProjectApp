package com.example.quizprojectapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(val repository: QuizRepository):ViewModel() {

    private val _programmer = MutableStateFlow(State())
    val programmer : StateFlow<State> = _programmer


    fun loadProgrammer(id:String) {
        viewModelScope.launch {
            val programm = repository.getProgrammer(id)
            _programmer.value = _programmer.value.copy(programmer = programm)
        }
    }

    fun loadQuestionsCplus(name:String) {
        viewModelScope.launch {
            val questionsCplus = repository.getQuestionsCplus(name)
            _programmer.value = _programmer.value.copy(questionsCplus = questionsCplus)
        }
    }

    fun loadQuestionsJava(name:String) {
        viewModelScope.launch {
            val questionsJava = repository.getQuestionsJava(name)
            _programmer.value = _programmer.value.copy(questionsJava = questionsJava)
        }
    }

    fun loadQuestionsPython(name:String) {
        viewModelScope.launch {
            val questionsPythone = repository.getQuestionsPython(name)
            _programmer.value = _programmer.value.copy(questionsPythone = questionsPythone)
        }
    }
}