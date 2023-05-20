package com.shamilov.core.android.ui.exam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shamilov.core.domain.entity.ExamStep

internal data class ExamStepItem(
    val target: String,
    val variants: List<String>,
)

@Composable
fun ExamScreen(
    viewModel: ExamViewModel,
) {
    val state by viewModel.viewState.collectAsState()
    ExamContent(
        state = state,
        variantClicked = { viewModel.nextStep() }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ExamContent(
    state: ExamUiState,
    variantClicked: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonWidth = screenWidth / 2 - 16.dp

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .padding(32.dp)
                .size(200.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.examStep.targetWord)
            }
        }

        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            state.examStep.variants.forEach {
                Button(
                    onClick = variantClicked,
                    modifier = Modifier.width(buttonWidth),
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
fun NeedMinCardsCount() {
    Text(text = "Что бы начать экзамен нужно добавить хотя бы 5 карточек")
}

@Preview
@Composable
fun ExamScreenPreview() {
    ExamContent(
        state = ExamUiState(
            ExamStep(
                "Hello",
                listOf(
                    "Привет",
                    "Дерево",
                    "Собака",
                    "Кошка",
                ),
            )
        )
    ) {

    }
}