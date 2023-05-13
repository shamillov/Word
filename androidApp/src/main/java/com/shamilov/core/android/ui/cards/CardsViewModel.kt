package com.shamilov.core.android.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.android.ui.components.CardItem
import com.shamilov.core.data.db.Card
import com.shamilov.core.data.entity.CardStatus
import com.shamilov.core.data.repository.CardRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CardsViewModel : ViewModel(), KoinComponent {

    private val repository: CardRepository by inject()

    private val _viewState = MutableStateFlow(value = CardsUiState.initState())
    internal val viewState: StateFlow<CardsUiState> = _viewState

    init {
        viewModelScope.launch {
            repository.observeCards().collectLatest { cards ->
                _viewState.update {
                    it.copy(cards = mapCardsToUi(cards))
                }
            }
        }
    }

    fun accept(message: CardsMessage) = when (message) {
        is CardsMessage.TranslationVisible -> _viewState.update { it.copy(translationVisible = message.visible) }
    }

    fun saveCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
    ) {
        repository.insertCard(
            word = word,
            translation = translation,
            category = category,
            example = example,
            status = CardStatus.NEW.name,
        )
    }

    fun editCard(id: Long) {

    }

    fun deleteCard(id: Long) {
        repository.deleteCard(id)
    }

    private fun mapCardsToUi(list: List<Card>): List<CardItem> {
        return list.map {
            CardItem(
                id = it.id,
                word = it.word,
                translation = it.translation,
                status = it.status,
                category = it.category,
                example = it.example,
            )
        }
    }
}