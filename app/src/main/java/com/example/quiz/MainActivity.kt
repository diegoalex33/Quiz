package com.example.quiz

import android.graphics.Color
import android.graphics.Color.*
import android.graphics.PorterDuff
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
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
lateinit var megadeth: ImageView
lateinit var metallica : ImageView

lateinit var quiz : Quiz
val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgits()

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
        metallica.visibility = View.GONE
        quiz = Quiz(questions)

        updateQuestion()


        trueButton.setOnClickListener {
            display(quiz.check(true))
            updateQuestion()
        }

        falseButton.setOnClickListener {
            display(quiz.check(false))
            updateQuestion()
        }

    }

    fun display(bool: Boolean){
        if(bool){
            trueButton.setBackgroundColor(GREEN)
            falseButton.setBackgroundColor(GREEN)
        }
        else{
            trueButton.setBackgroundColor(RED)
            falseButton.setBackgroundColor(RED)
        }
        scoreText.text = resources.getString(R.string.main_score) + ": " + quiz.score
    }

    fun updateQuestion(){
        if(!quiz.qRemaining()){
            endGame()
        }
        else{
            quiz.nextQuestion()
        }
    }

    fun endGame() {
        finalText.visibility = View.VISIBLE
        metallica.visibility = View.VISIBLE
        finalText.text = resources.getString(R.string.main_message) + quiz.score
        questionText.visibility = View.GONE
        falseButton.visibility = View.GONE
        trueButton.visibility = View.GONE
        scoreText.visibility = View.GONE
    }

    fun wireWidgits() {
        metallica = findViewById(R.id.imageView_main_metallica)
        megadeth = findViewById(R.id.imageView_main_megadeth)
        questionText = findViewById(R.id.textView_main_question)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        scoreText = findViewById(R.id.textView_main_score)
        finalText = findViewById(R.id.textView_main_message)
    }
}