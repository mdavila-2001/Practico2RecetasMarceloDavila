package com.mdavila_2001.practicorecetasmarcelodavila

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
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
                    NavigationApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticoRecetasMarceloDavilaTheme {
        Greeting("Android")
    }
}