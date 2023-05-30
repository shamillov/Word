package com.shamilov.core.android.ui.cards

import com.shamilov.core.android.ui.components.CardItem

internal data class CardsUiState(
    val cards: List<CardItem>,
    val cardsTranslationVisible: Boolean,
) {
    companion object {
        fun initState() = CardsUiState(
            cards = emptyList(),
            cardsTranslationVisible = false,
        )
    }
}
