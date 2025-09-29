package com.mdavila_2001.practicorecetasmarcelodavila.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.RecipeList
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    vm: RecipeViewmodel,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Todas las Recetas",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                },
                modifier
                    .padding(bottom = 4.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF16FA2C),
                                Color(0xFF28E4FA)
                            ),
                            start = Offset(-200f, 0f),
                            end = Offset(500f, 1000f)
                        )
                    ),
                colors = TopAppBarDefaults.topAppBarColors(
                    Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        RecipeList(
            recipes = vm.recipes,
            onRecipeClick = onRecipeClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview() {
    RecipeListScreen(
        vm = RecipeViewmodel(),
        onRecipeClick = {},
        modifier = Modifier
    )
}