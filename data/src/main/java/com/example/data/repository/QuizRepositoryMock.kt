package com.example.data.repository

import com.example.data.R
import com.example.domain.model.ProgrammerModel
import com.example.domain.model.QuestionsModel
import com.example.domain.repository.QuizRepository



class QuizRepositoryMock:QuizRepository {
    override fun getProgrammer(id: String): List<ProgrammerModel> {
        return listOf(
            ProgrammerModel("1", R.drawable.java, "Java"),
            ProgrammerModel("2", R.drawable.python, "Python"),
            ProgrammerModel("3", R.drawable.c, "C++"),
        )

    }

    override fun getQuestionsJava(name: String): List<QuestionsModel> {
        return listOf(
            QuestionsModel(
                "Что такое Java?",
                "Java", listOf("Java - это язык программирования", "Java - это база данных", "Java - это операционная система", "Java - это фреймворк"), "0"
            ),
            QuestionsModel(
                "Как объявить переменную в Java?",
                "Java", listOf("variable x;", "int x;", "x = int;", "x as Integer;"), "1"
            ),
            QuestionsModel(
                "Что такое оператор if в Java?",
                "Java", listOf("Оператор if - это цикл", "Оператор if - это оператор сравнения", "Оператор if - это функция", "Оператор if - это условный оператор"), "3"
            ),
            QuestionsModel(
                "Как создать объект в Java?",
                "Java", listOf("object x = new Object();", "Object x = new object();", "Object x = create Object();", "Object x = Object.create();"), "0"
            ),
            QuestionsModel(
                "Что такое цикл for в Java?",
                "Java", listOf("Цикл for - это оператор присваивания", "Цикл for - это оператор инкремента", "Цикл for - это цикл с постусловием", "Цикл for - это цикл с предусловием"), "2"
            ),
            // Добавьте другие вопросы и ответы по аналогии
        )
    }

    override fun getQuestionsPython(name: String): List<QuestionsModel> {
        return listOf(
            QuestionsModel(
                "Что такое Python?",
                "Python", listOf("Python - это высокоуровневый язык программирования", "Python - это база данных", "Python - это операционная система", "Python - это фреймворк"), "0"
            ),
            QuestionsModel(
                "Как объявить переменную в Python?",
                "Python", listOf("variable = value", "var value = variable", "имя_переменной = значение", "declare variable as value"), "2"
            ),
            QuestionsModel(
                "Что такое список (list) в Python?",
                "Python", listOf("Упорядоченная коллекция элементов, которые могут быть разных типов", "Тип данных для хранения одного значения", "Структура для хранения ключей и значений", "Оператор для выполнения цикла"), "0"
            ),
            QuestionsModel(
                "Какой цикл используется для перебора элементов списка в Python?",
                "Python", listOf("Цикл for", "Цикл while", "Цикл repeat", "Цикл foreach"), "0"
            ),
            QuestionsModel(
                "Что делает оператор if в Python?",
                "Python", listOf("Выполняет блок кода, если условие истинно", "Выполняет цикл до тех пор, пока условие истинно", "Определяет новую переменную", "Вызывает функцию"), "0"
            ),
            // Добавьте другие вопросы и ответы по аналогии
        )
    }
    override fun getQuestionsCplus(name: String): List<QuestionsModel> {
        return listOf(
            QuestionsModel(
                "Что такое переменная в C++?",
                "C++", listOf("Переменная - это название функции", "Переменная - это адрес памяти", "Переменная - это контейнер для хранения данных", "Переменная - это оператор"), "2"
            ),
            QuestionsModel(
                "Как объявить целочисленную переменную в C++?",
                "C++", listOf("int x;", "integer x;", "x = int;", "x as Integer;"), "0"
            ),
            QuestionsModel(
                "Что такое оператор if в C++?",
                "C++", listOf("Оператор if - это цикл", "Оператор if - это функция", "Оператор if - это оператор сравнения", "Оператор if - это условный оператор"), "3"
            ),
            QuestionsModel(
                "Что такое цикл while в C++?",
                "C++", listOf("Цикл while - это оператор присваивания", "Цикл while - это цикл с предусловием", "Цикл while - это функция", "Цикл while - это логический оператор"), "1"
            ),
            QuestionsModel(
                "Какой тип данных используется для хранения дробных чисел в C++?",
                "C++", listOf("float", "integer", "char", "string"), "0"
            ),
            // Добавьте другие вопросы и ответы по аналогии
        )
    }
}