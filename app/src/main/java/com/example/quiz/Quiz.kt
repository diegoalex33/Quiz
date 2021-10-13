package com.example.quiz

import android.view.View

class Quiz (val questions: List<MainActivity.Question>) {
    val numQuestions = questions.size
    var score = 0
    var questionNum = -1


    fun nextQuestion() {
        questionNum++
        if (questionNum != questions.size) {
            questionText.text = "" + questions.get(questionNum).question
        }
    }

    fun qRemaining(): Boolean{

    }


    fun check(tf: Boolean): Boolean {
        if (questions.get(questionNum).answer == tf) {
            score++
            return true
        } else {
            return false
        }
    }


}