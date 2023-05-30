package com.shamilov.core.android.ui.exam

import androidx.lifecycle.ViewModel
import com.shamilov.core.domain.usecase.ExamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ExamViewModel(
    private val examUseCase: ExamUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(ExamUiState(examUseCase.getExamStep()))
    internal val viewState: StateFlow<ExamUiState> = _viewState

    init {
        _viewState.update {
            val step = examUseCase.getExamStep()
            it.copy(examStep = step)
        }
    }

    fun nextStep() {
        _viewState.update {
            val nextStep = examUseCase.getExamStep()
            it.copy(examStep = nextStep)
        }
    }
}