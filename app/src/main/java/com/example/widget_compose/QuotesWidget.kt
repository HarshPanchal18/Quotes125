package com.example.widget_compose

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontFamily
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.example.widget_compose.action.RefreshQuoteAction
import com.example.widget_compose.repository.QuoteRepository

class QuotesWidget : GlanceAppWidget() {

    /*
    * GlanceStateDefinition - Telling GlanceState how to store-retrieve data.
                            - Once the object is created, the data is updating using the state directly.
    * PreferencesGlanceStateDefinition - For creating a widget using datastore preference
    * */
    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {

            val preferences = currentState<Preferences>()
            val repo = QuoteRepository()
            val currentQuote = preferences[repo.currentQuotePreferenceKey] ?: repo.getRandomQuote()

            MaterialTheme {
                Column(
                    modifier = GlanceModifier
                        .background(repo.getRandomColor().copy(alpha = 0.85F))
                        .padding(8.dp)
                        .clickable(actionRunCallback<RefreshQuoteAction>()),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = currentQuote,
                        style = TextStyle(fontFamily = FontFamily.Monospace),
                        modifier = GlanceModifier.fillMaxWidth()
                    )
                    Box(
                        modifier = GlanceModifier
                            .background(ImageProvider(R.mipmap.ic_launcher))
                            .size(20.dp)
                    ) {}
                }
            }
        }
    }
}
