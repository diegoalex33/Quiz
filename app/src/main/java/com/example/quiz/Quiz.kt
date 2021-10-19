package com.example.quiz

import android.view.View

class Quiz (val questions: List<Question>) {
    val numQuestions = questions.size
    var score = 0
    var questionNum = 0


    fun nextQuestion(){
        if (questionNum < questions.size) {
            questionText.text = "" + questions.get(questionNum).question
        }
    }

    fun qRemaining(): Boolean{
        return questionNum < questions.size
    }

    fun check(tf: Boolean): Boolean {
        if (questions.get(questionNum).answer == tf) {
            score++
            questionNum++
            return true
        } else {
            questionNum++
            return false
        }
    }

}