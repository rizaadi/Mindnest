package com.zephysus.mindnest.feature.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun HabitRoute(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = hiltViewModel(),
) {
    val habitState by viewModel.habitUiState.collectAsStateWithLifecycle()

    HabitScreen(
        habitState = habitState,
        modifier = modifier
    )
}

@Composable
internal fun HabitScreen(
    habitState: HabitUiState,
    modifier: Modifier = Modifier,
) {
    when (habitState) {
        HabitUiState.Loading -> Text("Loading")
        is HabitUiState.Success -> if (habitState.habit.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()

            ) {
                items(
                    items = habitState.habit,
                    key = { it.id },
                    contentType = { "habitItem" },
                ) { habitResource ->
                    Column {
                        Text(habitResource.id)
                        Text(habitResource.name)
                        Text(habitResource.createdAt.toString())
                    }
                }
            }
        } else {
            Text("Empty")
        }
    }
}

