package com.softworkshub.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        val userName : TextView = findViewById(R.id.user_name_result)
        val resultScore :TextView = findViewById(R.id.result_score)
        val btnFinal :Button = findViewById(R.id.btn_last_finish)


    }


}