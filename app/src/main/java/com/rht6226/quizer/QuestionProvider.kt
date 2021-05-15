package com.rht6226.quizer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class QuestionProvider(val context: Context) {

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun parseJsonData(): ArrayList<QuestionData> {
        val json = getJsonDataFromAsset(context, "questions.json")
        val gson = Gson()
        val arrayType = object : TypeToken<ArrayList<QuestionData>>() {}.type
        val questionList: ArrayList<QuestionData> = gson.fromJson(json, arrayType)
        questionList.shuffle()
        return questionList.slice(10 until 20) as ArrayList<QuestionData>
    }
}