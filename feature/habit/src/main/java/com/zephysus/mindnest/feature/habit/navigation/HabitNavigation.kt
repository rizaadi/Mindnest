package com.zephysus.mindnest.feature.habit.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zephysus.mindnest.feature.habit.HabitRoute
import com.zephysus.mindnest.feature.habit.CreateHabitRoute
import kotlinx.serialization.Serializable

@Serializable
data object HabitRoute

fun NavController.navigateToHabit(navOptions: NavOptions) = navigate(route = HabitRoute, navOptions)

fun NavGraphBuilder.habitScreen(
//    showBackButton: Boolean,
//    onBackClick: () -> Unit,
    onCreateHabitClick: () -> Unit,
) {
    composable<HabitRoute> {
        HabitRoute(
            onCreateHabitClick = onCreateHabitClick
        )
    }
}

@Serializable
data object CreateHabitRoute

fun NavController.navigateToCreateHabit(navOptions: NavOptions? = null) =
    navigate(route = CreateHabitRoute, navOptions)

fun NavGraphBuilder.createHabitScreen(
    onBackClick: () -> Unit,
) {
    composable<CreateHabitRoute> {
        CreateHabitRoute(
            onBackClick = onBackClick
        )
    }
}