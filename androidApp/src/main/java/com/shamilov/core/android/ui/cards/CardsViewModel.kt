package com.shamilov.core.android.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.android.ui.components.CardItem
import com.shamilov.core.data.db.Word
import com.shamilov.core.data.entity.CardStatus
import com.shamilov.core.data.repository.WordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CardsViewModel(
    private val repository: WordsRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow(value = CardsUiState.initState())
    val viewState: StateFlow<CardsUiState> = _viewState

    init {
        viewModelScope.launch {
            repository.observeWords().collectLatest { words ->
                _viewState.update { state ->
                    state.copy(cards = mapCardsToUi(words))
                }
            }
        }
    }

    fun changeTranslationVisible(current: CardItem) {
        _viewState.update { state ->
            val cards = state.cards.map { card ->
                if (card.id == current.id) card.copy(translationVisible = !current.translationVisible) else card
            }

            state.copy(cards = cards)
        }
    }

    fun changeAllTranslationVisible(show: Boolean) {
        _viewState.update { state ->
            val cards = state.cards.map { card ->
                if (card.translationVisible == show) card else card.copy(translationVisible = show)
            }

            state.copy(
                cards = cards,
                cardsTranslationVisible = show,
            )
        }
    }

    fun saveCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
    ) {
        repository.insertWord(
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
        repository.deleteWord(id)
    }

    private fun mapCardsToUi(list: List<Word>): List<CardItem> {
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