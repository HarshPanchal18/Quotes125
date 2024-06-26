package com.example.widget_compose.action

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.example.widget_compose.ui.QuotesWidget
import com.example.widget_compose.repository.QuoteRepository

class RefreshQuoteAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(
            context = context,
            definition = PreferencesGlanceStateDefinition,
            glanceId = glanceId
        ) { prefs ->
            val repo = QuoteRepository(context)
            prefs.toMutablePreferences()
                .apply {
                    this[repo.currentQuotePreferenceKey] = repo.getRandomQuote()
                }
        }
        QuotesWidget().update(context, glanceId)
    }
}
