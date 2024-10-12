package com.softworkshub.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mQuestionList: ArrayList<Question>? = null
    private var currentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var correctAnswerCount: Int = 0

    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var image: ImageView
    private lateinit var questionView: TextView
    private lateinit var btnSubmit: Button

    // Option variables
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        // Initialize views
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.textView_progress)
        image = findViewById(R.id.imageView)
        questionView = findViewById(R.id.tv_questions)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()

        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question: Question = mQuestionList!![currentPosition - 1]
        defaultOptionsView()

        btnSubmit.text = if (currentPosition == mQuestionList!!.size) "FINISHED" else "SUBMIT"

        progressBar.progress = currentPosition
        tvProgress.text = "$currentPosition/${progressBar.max+1}"

        questionView.text = question.question
        image.setImageResource(question.image)
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = listOf(optionOne, optionTwo, optionThree, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNo: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNo
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> selectedOptionView(optionOne, 1)
            R.id.tv_option_two -> selectedOptionView(optionTwo, 2)
            R.id.tv_option_three -> selectedOptionView(optionThree, 3)
            R.id.tv_option_four -> selectedOptionView(optionFour, 4)
            R.id.btn_submit -> handleButtonClick()
        }
    }

    private fun handleButtonClick() {
        if (mSelectedOptionPosition == 0) {
            currentPosition++
            if (currentPosition <= mQuestionList!!.size) {
                setQuestion()
            } else {
                navigateToResultScreen()
            }
        } else {
            val question = mQuestionList!![currentPosition - 1]
            if (question.correctOption != mSelectedOptionPosition) {
                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
            } else {
                correctAnswerCount++
            }
            answerView(question.correctOption, R.drawable.correct_option_border_bg)

            if (currentPosition == mQuestionList!!.size) {
                btnSubmit.text = "FINISHED"
            } else {
                btnSubmit.text = "GO TO THE NEXT QUESTION"
            }
            mSelectedOptionPosition = 0
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        val option = when (answer) {
            1 -> optionOne
            2 -> optionTwo
            3 -> optionThree
            4 -> optionFour
            else -> return
        }
        option.background = ContextCompat.getDrawable(this, drawableView)
    }

    private fun navigateToResultScreen() {
        Toast.makeText(this, "Congrats! You made it to the end", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ResultScreen::class.java).apply {
            putExtra(Constants.USER_NAME, mUserName)
            putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
            putExtra(Constants.CORRECT_ANSWER, correctAnswerCount)
        }
        startActivity(intent)
        finish()
    }
}
