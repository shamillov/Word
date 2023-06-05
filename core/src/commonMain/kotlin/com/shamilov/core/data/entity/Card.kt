package com.shamilov.core.data.entity

class Card(
    val id: Long,
    val word: String,
    val translation: String,
    val category: String?,
    val status: WordStatus,
    val example: String?,
)
