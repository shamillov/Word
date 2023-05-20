package com.shamilov.core.domain.usecase

import com.shamilov.core.data.repository.WordsRepository
import com.shamilov.core.domain.entity.ExamStep

class ExamUseCase(
    cardsRepository: WordsRepository,
) {

    private val cards = cardsRepository.getWords()

    private val cardsCount = cardsRepository.getWords().count()
    val examAvailable = cardsCount > 3

    fun getExamStep(): ExamStep {
        val card = cards.random()

        val wrongVariants = cards
            .filter { it != card }
            .take(3)
            .map { it.translation }
            .toMutableList()
            .apply { add(card.translation) }

        return ExamStep(
            targetWord = card.word,
            variants = wrongVariants.shuffled()
        )
    }
}