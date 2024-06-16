package com.example.widget_compose

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widget_compose.repository.QuoteRepository
import com.example.widget_compose.ui.theme.WidgetComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var repo: QuoteRepository
    private lateinit var listState: LazyListState
    private lateinit var coroutineScope: CoroutineScope
    private lateinit var quoteList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo = QuoteRepository(applicationContext)
        quoteList = repo.getQuoteList()

        setContent {
            WidgetComposeTheme {
                HomeScreen()

                this.showToast("Have you added Home Screen widget? :)")

                var pressedTime: Long = 0
                BackHandler(enabled = true) {
                    if (pressedTime + 2000 > System.currentTimeMillis())
                        finish()
                    else
                        this.showToast("Press again to exit")
                    pressedTime = System.currentTimeMillis()
                }
            }
        }
    }

    @Composable
    fun QuoteCard(currentQuote: String, index: Int) {
        Card(
            modifier = Modifier
                .padding(
                    bottom =
                    if (index == quoteList.lastIndex + 1) 80.dp
                    else 4.dp
                )
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val quoteClip = ClipData.newPlainText("quote", currentQuote)
                    cm.setPrimaryClip(quoteClip)
                    this.showToast("Copied to clipboard")
                },
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
                contentDescription = "App logo",
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(75.dp),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                )
                Text(
                    text = "Tap quote to copy.",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray
                )
            }
        }
    }

    @Composable
    fun HomeScreen() {
        listState = rememberLazyListState()
        coroutineScope = rememberCoroutineScope()

        Scaffold(
            topBar = { AppBar() },
            floatingActionButton = {
                BackToTopFAB {
                    coroutineScope.launch { listState.animateScrollToItem(index = 0) }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(state = listState) {
                    itemsIndexed(quoteList) { index, currentQuote ->
                        QuoteCard(currentQuote, index + 1)
                    }
                }
            }
        }
    }

    @Composable
    fun BackToTopFAB(onClick: () -> Unit) {
        FloatingActionButton(
            onClick = { onClick() },
            containerColor = Color(0xFFFFEB3B)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Back to top"
            )
        }
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
