package com.example.widget_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widget_compose.repository.QuoteRepository
import com.example.widget_compose.ui.theme.WidgetComposeTheme

class MainActivity : ComponentActivity() {

    private val repo = QuoteRepository()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetComposeTheme {
                Scaffold(
                    topBar = { AppBar() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        LazyColumn {
                            itemsIndexed(
                                repo.getQuoteList()
                            ) { index, currentQuote ->
                                QuoteCard(currentQuote, index + 1)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteCard(currentQuote: String, index: Int) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = currentQuote,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "$index",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp),
                fontWeight = FontWeight.Light,
                fontSize = 35.sp,
                color = Color.Gray.copy(0.6F)
            )
        }
    }
}

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 8.dp)
                .clip(RoundedCornerShape(24.dp))
                .size(75.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.weight(1F)
        )
    }
}
