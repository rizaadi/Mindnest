package com.zephysus.mindnest.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.zephysus.mindnest.app.navigation.MindnestNavHost
import kotlin.reflect.KClass

@Composable
fun MindnestApp(
    appState: MindnestAppState,
    modifier: Modifier = Modifier,
) {
//  TODO  handle snack bar Host State

    IMindnestApp(
        appState = appState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun IMindnestApp(
    appState: MindnestAppState,
    modifier: Modifier = Modifier,
) {
    val currentDestination = appState.currentDestination

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = modifier
            ) {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            appState.navigateToTopLevelDestination(destination)
                        },
                        icon = {
                            if (selected) {
                                Icon(
                                    imageVector = destination.selectedIcon,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = destination.unselectedIcon,
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier,
                        label = {
                            Text(stringResource(destination.iconTextId))
                        },
                    )
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            // Show the top app on top level destination
            val destination = appState.currentTopLevelDestination
            val shouldShowTopAppBar = false

            if (destination != null) {
                CenterAlignedTopAppBar(
                    title = { Text(text = stringResource(destination.titleTextId)) },
                    modifier = modifier,
                    navigationIcon = {},
                    actions = {}
                )
            }

            Box(
                // Workaround for https://issuetracker.google.com/338478720
                modifier = Modifier.consumeWindowInsets(
                    if (shouldShowTopAppBar) {
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    } else {
                        WindowInsets(0, 0, 0, 0)
                    },
                ),
            ) {
                MindnestNavHost(
                    appState = appState
                )
            }
        }
    }

}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) = this?.hierarchy?.any {
    it.hasRoute(route)
} ?: false