package com.example.quiz

import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

lateinit var questionText : TextView
lateinit var trueButton: Button
lateinit var falseButton: Button
lateinit var scoreText : TextView
lateinit var finalText : TextView
val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgits()

        lateinit var quiz: Quiz

        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${questions.toString()}")

        finalText.visibility = View.GONE

        quiz = Quiz(questions)

        quiz.nextQuestion()


        trueButton.setOnClickListener {
            if(quiz.check(true)){
                trueButton.setBackgroundColor(GREEN)
                falseButton.setBackgroundColor(GREEN)
            }
            else{
                trueButton.setBackgroundColor(RED)
                falseButton.setBackgroundColor(RED)
            }
            scoreText.text = resources.getString(R.string.main_score) + ": " + quiz.score
            quiz.nextQuestion()
        }
        falseButton.setOnClickListener {
            if(quiz.check(false)){
                trueButton.setBackgroundColor(GREEN)
                falseButton.setBackgroundColor(GREEN)            }
            else{
                trueButton.setBackgroundColor(RED)
                falseButton.setBackgroundColor(RED)            }
            scoreText.text = resources.getString(R.string.main_score) + ": " + quiz.score
            quiz.nextQuestion()
        }

    }

    data class Question(var question: String, var answer: Boolean) {

    }

    fun endGame() {
        finalText.visibility = View.VISIBLE
        finalText.text = resources.getString(R.string.main_score) + quiz.score
        falseButton.visibility = View.GONE
        trueButton.visibility = View.GONE
        scoreText.visibility = View.GONE
    }

    fun wireWidgits() {
        questionText = findViewById(R.id.textView_main_question)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        scoreText = findViewById(R.id.textView_main_score)
        finalText = findViewById(R.id.textView_main_message)
    }
}