package com.example.widget_compose

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
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
import androidx.glance.text.Text

private val currentQuotePreferenceKey = stringPreferencesKey("currentQuote")

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
            val currentQuote = preferences[currentQuotePreferenceKey] ?: quoteList.random()

            MaterialTheme {
                Box(
                    modifier = GlanceModifier
                        .background(colorList.random())
                        .padding(12.dp)
                        .clickable(actionRunCallback<RefreshQuoteAction>())
                ) {
                    Text(text = currentQuote)
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
                .apply { this[currentQuotePreferenceKey] = quoteList.random() }
        }
        QuotesWidget().update(context, glanceId)
    }
}

private val quoteList = listOf(
    "Be yourself; everyone else is already taken. ― Oscar Wilde",
    "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
    "You only live once, but if you do it right, once is enough. ― Mae West",
    "Life is the art of drawing without an eraser. ~ John W. Gardner",
    "The whole future lies in uncertainty: live immediately. ~  Seneca",
    "The reward for our work is not what we get, but what we become. ~ Paulo CoelHo",
    "Embrace your inner warrior, rise above the negativity, and stay focused on your goals",
    "Don't hold yourself back and always know that you are capable of anything you set your mind to.",
)

private val colorList: List<Color> = listOf(

    Color.White, Color.LightGray,
    Color(0xFF6750A4), Color(0xFF7D5260),
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
