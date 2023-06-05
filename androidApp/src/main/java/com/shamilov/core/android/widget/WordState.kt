package com.shamilov.core.android.widget

import kotlinx.serialization.Serializable

/**
 * @author Shamilov on 02.06.2023
 */
@Serializable
internal sealed interface WordState {

    @Serializable
    object Loading : WordState

    @Serializable
    class Available(val word: WordUiData) : WordState

    @Serializable
    class Empty(val message: String) : WordState
}

@Serializable
class WordUiData(
    val id: Long,
    val word: String,
    val translation: String,
)
