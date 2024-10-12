package com.softworkshub.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.btn_start)
        val editTextN : EditText = findViewById(R.id.et_text)
        startButton.setOnClickListener {
            if (editTextN.text.isEmpty()){
                Toast.makeText(this, "Enter the name first ", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,editTextN.text.toString())
                startActivity(intent)
                finish()
            }
        }



    }
}