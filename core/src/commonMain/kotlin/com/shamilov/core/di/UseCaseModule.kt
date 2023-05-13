package com.shamilov.core.di

import com.shamilov.core.domain.usecase.ExamUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::ExamUseCase)
}
