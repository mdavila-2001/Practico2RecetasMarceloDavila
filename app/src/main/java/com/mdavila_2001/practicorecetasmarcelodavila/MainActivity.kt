package com.mdavila_2001.practicorecetasmarcelodavila

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.BottomNavigationBar
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.NavigationApp
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.IngredientSelectScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeListScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.theme.PracticoRecetasMarceloDavilaTheme
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticoRecetasMarceloDavilaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    IngredientSelectScreen(
//                        vm = RecipeViewmodel(),
//                        onSearch = {},
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    RecipeListScreen(
//                        vm = RecipeViewmodel(),
//                        onRecipeClick = {},
//                        onRecipeAdd = {},
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    NavigationApp(
//                        modifier = Modifier.padding(innerPadding)
//                    )
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

    Scaffold(
        bottomBar = {
            // Aquí usamos el BottomNavigationBar que pasamos el estado seleccionado
            BottomNavigationBar(
                selectedTab = selectedTab.value,
                onTabSelected = onTabSelected,
                modifier = Modifier
            )
        }
    ) { innerPadding->
        NavigationApp(
            selectedTab = selectedTab.value,
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