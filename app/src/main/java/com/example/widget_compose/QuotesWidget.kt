package com.example.widget_compose

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition

private val quotes = listOf(
    "Be yourself; everyone else is already taken. ― Oscar Wilde",
    "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
    "You only live once, but if you do it right, once is enough. ― Mae West",
)

private val currentQuoteKey = stringPreferencesKey("currentQuote")

class QuotesWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val preferences = currentState<Preferences>()
            val currentQuote = preferences[currentQuoteKey] ?: quotes.random()

            MaterialTheme {
                Box(
                    modifier = GlanceModifier
                        .background(Color.White)
                        .padding(16.dp)
                        .clickable(actionRunCallback<RefreshQuoteAction>())
                ) {
                    Text(text = currentQuote)
                    Text(text = LocalContext.current.getString(R.string.app_name))
                }
            }
        }
    }
}

class RefreshQuoteAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(
            context,
            PreferencesGlanceStateDefinition,
            glanceId
        ) { prefs ->
            prefs.toMutablePreferences()
                .apply { this[currentQuoteKey] = quotes.random() }
        }
        QuotesWidget().update(context, glanceId)
    }
}
