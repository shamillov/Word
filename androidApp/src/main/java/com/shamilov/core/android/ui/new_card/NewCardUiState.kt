package com.shamilov.core.android.ui.new_card

internal data class NewCardUiState(
    val word: String = "",
    val translate: String = "",
    val category: String = "",
    val example: String = "",
    val addOnMoreWord: Boolean = false,
)