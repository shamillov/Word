package com.shamilov.core.android.di

import com.shamilov.core.android.ui.cards.CardsViewModel
import com.shamilov.core.android.ui.exam.ExamViewModel
import com.shamilov.core.android.ui.new_card.NewCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

internal val androidModule: Module = module {
    viewModelOf(::CardsViewModel)
    viewModelOf(::NewCardViewModel)
    viewModelOf(::ExamViewModel)
}
