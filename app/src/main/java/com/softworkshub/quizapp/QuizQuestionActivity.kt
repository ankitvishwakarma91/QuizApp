package com.softworkshub.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener {

    private var mQuestionList : ArrayList<Question>? = null
    private var currentPosition :Int = 1
    private var mSelectedOptionPosition : Int  = 0
    private var mUserName: String? = null
    private var correctAnswerCount :Int = 0

    private var progressBar : ProgressBar? = null
    private var tvprogress : TextView? = null
    private var image : ImageView? = null
    private var questionView : TextView? = null
    private var btnSubmit : Button? =  null

    //Options variable
    private  var optionOne : TextView? = null
    private  var optionTwo : TextView? = null
    private  var optionThree : TextView? = null
    private  var optionFour : TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        progressBar = findViewById(R.id.progressBar)
        tvprogress = findViewById(R.id.textView_progress)
        image = findViewById(R.id.imageView)
        questionView = findViewById(R.id.tv_questions)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestion()

        setQuestion()
        defaultOptionsView()

    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionList!![currentPosition - 1]
        image?.setImageResource(question.image)
        progressBar?.progress = currentPosition
        tvprogress?.text = "$currentPosition/${progressBar?.max}"
        questionView?.text = question.question
        optionOne?.text = question.optionOne
        optionTwo?.text = question.optionTwo
        optionThree?.text = question.optionThree
        optionFour?.text = question.optionFour

        if (currentPosition == mQuestionList!!.size){
            btnSubmit?.text = "FINISHED"
        }else{
            btnSubmit?.text= "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        optionOne?.let {
            options.add(0,it)
        }
        optionTwo?.let {
            options.add(1,it)
        }
        optionThree?.let {
            options.add(2,it)
        }
        optionFour?.let {
            options.add(3,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNo : Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNo
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one ->{
                optionOne?.let {
                    selectedOptionView(it,mSelectedOptionPosition)
                }
            }

            R.id.tv_option_two -> {
                optionTwo?.let {
                    selectedOptionView(it, mSelectedOptionPosition)
                }
            }
            R.id.tv_option_three -> {
                optionThree?.let {
                    selectedOptionView(it, mSelectedOptionPosition)
                }
            }
            R.id.tv_option_four -> {
                optionFour?.let {
                    selectedOptionView(it, mSelectedOptionPosition)
                }
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0){
                    currentPosition++
                    when{
                        currentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "Congrats! You made it to the end",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,ResultScreen::class.java )
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTION,mQuestionList?.size)
                            intent.putExtra(Constants.CORRECT_ANSWER,correctAnswerCount)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    var question = mQuestionList?.get(currentPosition-1)
                    if (question!!.correctOption != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        correctAnswerCount++
                    }
                    answerView(question.correctOption ,R.drawable.correct_option_border_bg)
                }

                if (currentPosition == mQuestionList!!.size){
                    btnSubmit?.text = "FINISHED"
                }else{
                    btnSubmit?.text = "GO TO THE NEXT QUESTION"
                }

                mSelectedOptionPosition = 0
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}