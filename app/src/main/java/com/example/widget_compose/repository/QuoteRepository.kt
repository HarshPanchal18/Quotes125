package com.example.widget_compose.repository

import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.stringPreferencesKey

class QuoteRepository {

    val currentQuotePreferenceKey = stringPreferencesKey("currentQuote")

    private val quoteList = listOf(
        "Be yourself; everyone else is already taken. ― Oscar Wilde",
        "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
        "You only live once, but if you do it right, once is enough. ― Mae West",
        "Life is the art of drawing without an eraser. ― John W. Gardner",
        "The whole future lies in uncertainty: live immediately. ―  Seneca",
        "The reward for our work is not what we get, but what we become. ― Paulo CoelHo",
        "Embrace your inner warrior, rise above the negativity, and stay focused on your goals.",
        "Don't hold yourself back and always know that you are capable of anything you set your mind to.",
        "The people who are crazy enough to think they can change the world are the ones who do.",
        "Wait for the one but don't wait for someone to be the one.",
        "What we know is a drop, what we don't know is an ocean. ― Isaac Newton",
        "Do not give up, the beginning is always the hardest.",
        "Sometimes trying to prove that you are the best is an insult!",
        "Do hard things daily. Stop quitting when it gets hard.",
        "Dear God, Today I woke up, I am healthy, I am alive. Thank you!",
        "The fear we don't face become our limits.",
        "You can either do it now or regret it later.",
        "Keep your struggle a secret, until it succeed.",
        "Grind for your dream like your life depends on it. Because it does.",
        "You will never found rainbow if you are looking down.",
        "If you spend too much time thinking about a thing, you will never get it done.",
        "One day, all the early mornings, late nights, and sacrifices will make you a legend.",
        "It may take a month, a year, or a decade but if you want it bad enough, you will make it happen.",
        "Inhale courage, exhale fear.",
        "Not all storms come to disrupt your life, some come to clear your path.",
        "Stay original and let the world copy you.",
        "Champions don't show up to get everything they want. They show up to give everything they have.",
        "To be calm is the highest achievement of the self.",
        "Be tolerant with others and strict with yourself. ― Marcus Aurelius",
        "Choose your happiness over everything.",
        "Do not confuse denial with hope.",
        "The good things you make, make you good!",
        "Before you can hit the jackpot, you have to put a coin in the machine.",
        "The more you listen, the more you hear.",
        "Make sure you remember to smile today!",
        "Do not attend every argument you are invited to!",
        "Persistent and consistent effort over time can yield results.",
        "Do not forget forgiveness is something we do for others!",
        "Other peoples opinions are the biggest poison to your happiness.",
        "The journey of a thousand mils begins with a single step. ~ Lao Tzu",
        "They don't like you, cause they are not like you!",
        "Your fear don't know your strength.",
        "Tough times don't last, tough people do.",
        "So far you've survived 100% of your worst days.",
        "Anyone trying yo put you down is already below you.",
        "Know when to hustle and when to rest."
    )

    fun getRandomQuote(): String {
        return quoteList.random()
    }

    fun getQuoteList(): List<String> {
        return quoteList
    }

    /*private fun quotesFromJson() {
        val jsonData = Quotes(quoteList)
        val jsonArray = JSONArray(jsonData)
        val quotes = ArrayList<String>()

        for(i in 0 until jsonArray.length()){
            quotes.add(jsonArray[i].toString())
            println(quotes[i])
        }

        //val content = context.assets.readAssetsFile("quotes.json")
    }

    private fun AssetManager.readAssetsFile(filename: String): String {
        return open(filename).bufferedReader().use { it.readText() }
    }*/

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
