package com.rht6226.quizer

import android.content.Context
import java.io.IOException

class Countries(context: Context) {
    data class Country(val code: String, val name: String, val flag: String)

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}