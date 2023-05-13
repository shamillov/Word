package com.shamilov.core.domain.usecase

import com.shamilov.core.data.repository.CardRepository
import com.shamilov.core.domain.entity.ExamStep

class ExamUseCase(
    cardsRepository: CardRepository,
) {

    private val cards = cardsRepository.getCards()

    val cardsCount = cardsRepository.getCards().count()

    fun getExamStep(): ExamStep {
        val card = cards.random()

        val wrongVariants = cards
            .filter { it != card }
            .take(3)
            .map { it.translation }

        return ExamStep(
            targetWord = card.word,
            variants = (wrongVariants + listOf(card.translation)).shuffled()
        )
    }
}