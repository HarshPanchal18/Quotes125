package com.example.widget_compose.repository

import android.content.Context
import androidx.compose.ui.graphics.Color
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

    private val colorList: List<Color> = listOf(
        Color.White, Color.LightGray,
        Color(0xFF6750A4), Color(0xFF775260),
        Color(0xFFEFB8C8), Color(0xFFF2B8B5),
        Color(0xFF938F99), Color(0xFFFEDAB3),
        Color(0xFFAECAB3), Color(0xFFDDBAF7),
        Color(0xFFAC7B55), Color(0xFFFFEB3B),
        Color(0xFFE8E638), Color(0xFFA8E638),
        Color(0xFF4DD874), Color(0xFF4DA478),
        Color(0xFF985EDB), Color(0xFFD65A82),
        Color(0xFFA65A8A), Color(0xFF6FDDBE),
        Color(0xFFAFDDBE), Color(0xFFAF3DBE),
        Color(0xFF4186DA), Color(0xFFB186DA),
        Color(0xFFEF8A6A), Color(0xFFDC60F1),
        Color(0xFF81E485), Color(0xFFED4747),
        Color(0xFF97ED47), Color(0xFF47CFED),
        Color(0xFF6BD19B), Color(0xFF92D16B),
    )

    fun getRandomColor(): Color {
        return colorList.random()
    }
}
