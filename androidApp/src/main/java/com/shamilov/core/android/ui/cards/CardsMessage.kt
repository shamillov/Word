package com.shamilov.core.android.ui.cards

sealed class CardsMessage {
    class TranslationVisible(val visible: Boolean) : CardsMessage()
}