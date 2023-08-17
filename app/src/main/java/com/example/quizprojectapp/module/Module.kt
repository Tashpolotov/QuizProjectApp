package com.example.quizprojectapp.module

import com.example.data.repository.QuizRepositoryMock
import com.example.domain.repository.QuizRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

    @Provides
    @Singleton
    fun provideRepository():QuizRepository{
        return QuizRepositoryMock()
    }

}