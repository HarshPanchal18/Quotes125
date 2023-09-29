package com.example.widget_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val repo = QuoteRepository()
    private lateinit var listState: LazyListState
    private lateinit var coroutineScope: CoroutineScope
    //private var isScrollingDown by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetComposeTheme {
                HomeScreen()
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

    @OptIn(ExperimentalMaterial3Api::class)
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
                    itemsIndexed(
                        repo.getQuoteList()
                    ) { index, currentQuote ->
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

    /*@Composable
    fun LazyListState.isScrollingDown(): Boolean {
        var prevIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
        var prevScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
        return remember(this) {
            derivedStateOf {
                if (prevIndex != firstVisibleItemIndex) {
                    prevIndex > firstVisibleItemIndex
                } else {
                    prevScrollOffset >= firstVisibleItemScrollOffset
                }.also {
                    prevIndex = firstVisibleItemIndex
                    prevScrollOffset = firstVisibleItemScrollOffset
                }
            }.value
        }
    }*/
}
