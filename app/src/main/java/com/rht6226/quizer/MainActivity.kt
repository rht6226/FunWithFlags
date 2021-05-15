package com.rht6226.quizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.rht6226.quizer.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            if(binding.textViewName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent: Intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.textViewName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}