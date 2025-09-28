package com.mdavila_2001.practicorecetasmarcelodavila.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe

@Composable
fun RecipeItem(
    recipe: Recipe,
    onClick: (Recipe) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 6.dp)
            .clickable { onClick(recipe) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Ingredientes: ${recipe.ingredients.take(3).joinToString(", ")}${if (recipe.ingredients.size > 3) ", ..." else ""}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipe = Recipe(
            id = 1,
            name = "Spaghetti Carbonara",
            ingredients = listOf("Spaghetti", "Huevos", "Queso Parmesano", "Guanciale", "Pimienta Negra"),
            procedure = "Cocinar la pasta. Freír el guanciale hasta que esté crujiente. Batir los huevos con el queso parmesano y la pimienta negra. Mezclar todo junto con la pasta caliente."
        ),
        onClick = {}
    )
}