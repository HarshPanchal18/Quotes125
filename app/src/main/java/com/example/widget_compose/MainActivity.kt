package com.example.widget_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.widget_compose.repository.QuoteRepository
import com.example.widget_compose.ui.theme.WidgetComposeTheme

class MainActivity : ComponentActivity() {

    private val repo = QuoteRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetComposeTheme {
                // A surface container using the 'background' color from the theme
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(repo.getQuoteList()) { currentQuote ->
                            QuoteCard(currentQuote = currentQuote)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteCard(currentQuote: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = currentQuote,
            color = Color.Black,
            modifier = Modifier.padding(8.dp),
            fontFamily = FontFamily.Monospace
        )
    }
}
