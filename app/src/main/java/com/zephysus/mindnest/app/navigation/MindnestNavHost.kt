package com.zephysus.mindnest.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.zephysus.mindnest.app.ui.MindnestAppState
import com.zephysus.mindnest.feature.habit.navigation.HabitRoute
import com.zephysus.mindnest.feature.habit.navigation.createHabitScreen
import com.zephysus.mindnest.feature.habit.navigation.habitScreen
import com.zephysus.mindnest.feature.habit.navigation.navigateToCreateHabit

@Composable
fun MindnestNavHost(
    appState: MindnestAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HabitRoute,
        modifier = modifier,
    ) {
        habitScreen(
            onCreateHabitClick = navController::navigateToCreateHabit
        )
        createHabitScreen(
            onBackClick = navController::popBackStack
        )
    }
}