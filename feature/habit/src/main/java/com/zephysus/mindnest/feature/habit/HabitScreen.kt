package com.zephysus.mindnest.feature.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
internal fun HabitRoute(
    onCreateHabitClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = hiltViewModel(),
) {
    val habitState by viewModel.habitUiState.collectAsStateWithLifecycle()

    HabitScreen(
        onCreateHabitClick = onCreateHabitClick, habitState = habitState, modifier = modifier
    )
}

@Composable
internal fun HabitScreen(
    onCreateHabitClick: () -> Unit,
    habitState: HabitUiState,
    modifier: Modifier = Modifier,
) {
    when (habitState) {
        HabitUiState.Loading -> Text("Loading")
        is HabitUiState.Success -> if (habitState.habit.isNotEmpty()) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        onCreateHabitClick()
                    }) {
                        Text(
                            text = stringResource(R.string.feature_habit_create)
                        )
                    }
                }
                items(
                    items = habitState.habit,
                    key = { it.id },
                    contentType = { "habitItem" },
                ) { habitResource ->
                    Row {
                        Column {
                            Text(habitResource.id)
                            Text(habitResource.name)
                            Text(habitResource.createdAt.toString())
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(Icons.Rounded.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }

        } else {
            Column {
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    onCreateHabitClick()
                }) {
                    Text(
                        text = stringResource(R.string.feature_habit_create)
                    )
                }
                Text("Empty")
            }
        }
    }
}

