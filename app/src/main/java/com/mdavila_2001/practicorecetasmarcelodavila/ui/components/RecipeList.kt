package com.mdavila_2001.practicorecetasmarcelodavila.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onClick = onRecipeClick
            )
        }
    }
}