package com.shamilov.core.android.ui.new_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shamilov.core.data.entity.CardStatus
import com.shamilov.core.data.repository.WordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class NewCardViewModel(
    private val repository: WordsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(NewCardUiState())
    val state: StateFlow<NewCardUiState> = _state

    fun saveCard(
        word: String,
        translation: String,
        category: String?,
        example: String?,
        andOneMoreWord: Boolean
    ) {
        viewModelScope.launch {
            repository.insertWord(
                word = word,
                translation = translation,
                category = category,
                example = example,
                status = CardStatus.NEW.name,
            )
        }

        if (andOneMoreWord) {
            _state.update { state ->
                clearValues(state)
            }
            // show toast
        } else {
            // close screen
        }
    }

    private fun clearValues(state: NewCardUiState): NewCardUiState {
        return state.copy(
            word = "",
            translate = "",
            category = "",
            example = "",
        )
    }
}