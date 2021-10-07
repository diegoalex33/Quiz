package com.example.quiz

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

lateinit var questionText : TextView
lateinit var trueButton: Button
lateinit var falseButton: Button
val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgits()

        lateinit var quiz : Quiz


        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        val gson = Gson()
        val qType = object : TypeToken<List<Question>>() { }.type
        val questionList = gson.fromJson<List<Question>>(jsonText, qType)
        Log.d(TAG, "onCreate: \n${questionList.toString()}")

        quiz = Quiz(questionList)

        questionText.text = "Click to begin"
        trueButton.text = "True"
        falseButton.text = "False"

        trueButton.setOnClickListener {
            if(quiz.check(true)){

            }
            questionText.text = quiz.nextQuestion()

        }
        falseButton.setOnClickListener {
            if(quiz.check(false)){

            }
        }
    }

    data class Question(var q: String, var answer: Boolean){

    }

    fun wireWidgits() {
        questionText = findViewById(R.id.textView_main_question)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
    }
}