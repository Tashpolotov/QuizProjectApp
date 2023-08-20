package com.example.quizprojectapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class AppMain : Application() {
    // Этот класс наследует Application и используется для инициализации Hilt
    // Мы можем добавить сюда любой "мусорный" код для экспериментов, который не влияет на приложение
    // Например, неиспользуемые переменные, комментарии и т.д.

    // private val unusedVariable = "This is an unused variable."
    // // Или просто комментарии, которые описывают какой-либо "мусорный" код
    // // Этот комментарий не влияет на работу приложения

    // Или даже просто пустая функция
    // fun doNothing() {
    //     // Эта функция ничего не делает
    // }
}