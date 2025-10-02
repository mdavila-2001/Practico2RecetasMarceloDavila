package com.mdavila_2001.practicorecetasmarcelodavila

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.BottomNavigationBar
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.NavigationApp
import com.mdavila_2001.practicorecetasmarcelodavila.ui.theme.PracticoRecetasMarceloDavilaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticoRecetasMarceloDavilaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val selectedTab = remember { mutableStateOf(0) }

    val onTabSelected: (Int) -> Unit = { index ->
        selectedTab.value = index
    }

    val navController = rememberNavController()
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntryState.value?.destination?.route
    val showBottomBar = currentRoute != NavScreens.FORM.name

    Scaffold(
        bottomBar = {
            if (showBottomBar && (selectedTab.value == 0 || selectedTab.value == 1)) {
                BottomNavigationBar(
                    selectedTab = selectedTab.value,
                    onTabSelected = onTabSelected,
                    modifier = Modifier
                )
            }
        }
    ) { innerPadding ->
        NavigationApp(
            selectedTab = selectedTab.value,
            navController = navController,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PracticoRecetasMarceloDavilaTheme {
        MainScreen()
    }
}
