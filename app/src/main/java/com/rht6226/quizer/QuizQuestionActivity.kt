package com.rht6226.quizer

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rht6226.quizer.databinding.ActivityQuizQuestionBinding


class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizQuestionBinding
    private val questionsList: ArrayList<QuestionData> by lazy { QuestionProvider(this).parseJsonData() }
    private var currentPosition: Int = 1
    private var selectedOptionPosition: Int = 0
    private var mQuestionSubmitted = false
    private var mScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        binding.sumbit.setOnClickListener(this)
    }

    private fun loadQuestion() {

        val question: QuestionData = questionsList[currentPosition - 1]
        binding.progressBar.progress = currentPosition
        val progress = "$currentPosition / ${binding.progressBar.max}"
        binding.tvProgress.text = progress

        binding.sumbit.text = "SUBMIT"

        defaultOptionsView()

        val imageDrawable = this.resources.getIdentifier(
            question.image.split(".")[0],
            "drawable",
            Constants.PACKAGE_NAME
        )

        Log.d("Loader", imageDrawable.toString())

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)


        binding.tvQuestion.text = question.question
        binding.ivImage.setImageResource(imageDrawable)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour


    }

    private fun defaultOptionsView() {
        mQuestionSubmitted = false
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        if (!mQuestionSubmitted) {
            defaultOptionsView()
            selectedOptionPosition = selectedOptionNumber
            tv.setTextColor(Color.parseColor("#363A43"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun launchResultActivity() {
        val name = intent.getStringExtra(Constants.USER_NAME)
        val resultIntent = Intent(this, ResultActivity::class.java)
        resultIntent.putExtra(Constants.USER_NAME, name)
        resultIntent.putExtra(Constants.TOTAL_CORRECT_ANSWER, mScore)
        resultIntent.putExtra(Constants.TOTAL_QUESTIONS_COUNT, questionsList.size)
        startActivity(resultIntent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(v as TextView, 1)
            R.id.tv_option_two -> selectedOptionView(v as TextView, 2)
            R.id.tv_option_three -> selectedOptionView(v as TextView, 3)
            R.id.tv_option_four -> selectedOptionView(v as TextView, 4)

            R.id.sumbit -> {
                if (selectedOptionPosition == 0) {
                    currentPosition++

                    when {
                        currentPosition <= questionsList.size -> {
                            loadQuestion()
                        }

                        else -> launchResultActivity()
                    }
                } else {
                    val question = questionsList[currentPosition - 1]
                    if (question.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mScore++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    binding.sumbit.text = if (currentPosition != questionsList.size) {
                        "GO TO NEXT QUESTION"
                    } else {
                        "FINISH"
                    }
                    mQuestionSubmitted = true
                    selectedOptionPosition = 0
                }
            }

            else -> Log.e("QuizQuestionActivity", "Unexpected Error")
        }
    }

    override fun onBackPressed() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(this@QuizQuestionActivity)
        builder1.setMessage("You will loose all your progress. Are you sure you want to exit?")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Exit",
            DialogInterface.OnClickListener { _, _ -> super.onBackPressed() })

        builder1.setNegativeButton(
            "Go Back",
            DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })

        val dialog: AlertDialog = builder1.create()

        dialog.show()
    }
}