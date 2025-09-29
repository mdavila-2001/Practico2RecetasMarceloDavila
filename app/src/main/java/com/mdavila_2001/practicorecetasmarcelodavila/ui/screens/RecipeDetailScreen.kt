package com.mdavila_2001.practicorecetasmarcelodavila.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBackClick: () -> Unit,
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
                            text = recipe.name,
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

                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color.Transparent
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Ingredientes:",
                style = MaterialTheme.typography.titleMedium
            )
            recipe.ingredients.forEach { ing ->
                Text(
                    "• $ing",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Divider()
            Text(
                "Procedimiento:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                recipe.procedure,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(
        recipe = Recipe(
            id = 1,
            name = "Spaghetti Carbonara",
            ingredients = listOf("Spaghetti", "Huevos", "Queso Parmesano", "Guanciale", "Pimienta Negra"),
            procedure = "Cocinar la pasta. Freír el guanciale hasta que esté crujiente. Batir los huevos (solamente las yemas) con el queso parmesano y la pimienta negra. Mezclar todo junto con la pasta caliente."
        ),
        onBackClick = {},
        modifier = Modifier
    )
}