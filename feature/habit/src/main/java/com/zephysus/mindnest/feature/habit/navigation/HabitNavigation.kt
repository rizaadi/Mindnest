package com.zephysus.mindnest.feature.habit.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zephysus.mindnest.feature.habit.HabitRoute
import kotlinx.serialization.Serializable

@Serializable
data object HabitRoute

fun NavController.navigateToHabit(navOptions: NavOptions) = navigate(route = HabitRoute, navOptions)

fun NavGraphBuilder.habitScreen(
//    showBackButton: Boolean,
//    onBackClick: () -> Unit,
//    onTopicClick: (String) -> Unit,
) {
    composable<HabitRoute> {
        HabitRoute()
    }
}
