package com.example.widget_compose.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.widget_compose.model.Quote
import com.google.gson.Gson
import java.io.InputStream

class QuoteRepository(context: Context) {

    val currentQuotePreferenceKey = stringPreferencesKey("currentQuote")
    private val quotes = Gson().fromJson(loadJson(context), Quote::class.java)

    fun getRandomQuote(): String {
        return quotes.quoteList.random()
    }

    fun getQuoteList(): List<String> {
        return quotes.quoteList
    }

    private fun loadJson(context: Context): String? {
        lateinit var input: InputStream
        val jsonString: String

        try {
            input = context.assets.open("quotes.json")

            val size = input.available()
            val buffer = ByteArray(size) // Create buffer of size

            input.read(buffer) // Read data from stream to the buffer
            jsonString = String(buffer)

            return jsonString

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            input.close()
        }
        return null
    }
}
