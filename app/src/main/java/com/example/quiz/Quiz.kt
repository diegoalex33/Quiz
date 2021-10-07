package com.example.quiz

class Quiz (val questions: List<MainActivity.Question>) {
    val numQuestions = questions.size
    var score = 0
    var questionNum = 0

    fun nextQuestion(): String{
        questionNum++
        if(questionNum==questions.size){
            reset()
        }
        return questions.get(questionNum).q
    }

    fun check(tf : Boolean): Boolean {
        if (questions.get(questionNum).answer == tf) {
            score++
            return true
        }
        return false
    }

    fun reset(){
        score = 0

    }

}