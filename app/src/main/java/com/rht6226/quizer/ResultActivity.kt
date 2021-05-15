package com.rht6226.quizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rht6226.quizer.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val score = intent.getIntExtra(Constants.TOTAL_CORRECT_ANSWER, 0)
        val questions = intent.getIntExtra(Constants.TOTAL_QUESTIONS_COUNT, 10)

        binding.tvName.text = userName
        binding.tvScore.text = resources.getString(R.string.score, score, questions)

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}