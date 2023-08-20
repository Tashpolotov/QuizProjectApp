package com.example.quizprojectapp.settingfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel:ViewModel() {

    private val _musicYes = MutableStateFlow<String>("yes")
    val music : StateFlow<String> = _musicYes

    fun loadMusic(name:String) {
        viewModelScope.launch {
            _musicYes.emit(name)
        }
    }
}