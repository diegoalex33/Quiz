package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

lateinit var questionText : TextView
lateinit var trueButton: Button
lateinit var falseButton: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgits()


    }

    data class question(var q: String, var answer: Boolean)


    fun wireWidgits() {
        questionText = findViewById(R.id.textView_main_question)
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
    }
}