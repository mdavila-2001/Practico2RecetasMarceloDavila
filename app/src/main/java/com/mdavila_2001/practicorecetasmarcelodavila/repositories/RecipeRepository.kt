package com.mdavila_2001.practicorecetasmarcelodavila.repositories

import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe

object RecipeRepository {
    private val recipes = arrayListOf<Recipe>(
        Recipe(
            id = 1,
            name = "Spaghetti Carbonara",
            ingredients = listOf(
                "Spaghetti",
                "Huevos",
                "Queso Parmesano",
                "Guanciale",
                "Pimienta Negra"
            ),
            procedure = "Cocinar la pasta. Freír el guanciale hasta que esté crujiente. Batir los huevos con el queso parmesano y la pimienta negra. Mezclar todo junto con la pasta caliente."
        ),
        Recipe(
            id = 2,
            name = "Pollo al Curry",
            ingredients = listOf(
                "Pollo",
                "Polvo de Curry",
                "Leche de Coco",
                "Cebolla",
                "Ajo",
                "Jengibre"
            ),
            procedure = "Sofreír la cebolla, el ajo y el jengibre. Añadir el pollo y cocinar hasta que esté dorado. Agregar el polvo de curry y la leche de coco. Cocinar a fuego lento hasta que el pollo esté tierno."
        ),
        Recipe(
            id = 3,
            name = "Majadito Batido",
            ingredients = listOf(
                "Arroz",
                "Charque",
                "Cebolla",
                "Pimentón",
                "Ajo",
                "Urucú",
                "Huevos",
                "Plátano"
            ),
            procedure = "Cocinar el arroz aparte. Sofreír la cebolla, pimentón y ajo en aceite. Agregar el charque desmenuzado y el urucú para darle color al arroz. Incorporar el arroz cocido y mezclar bien hasta que todo quede uniforme. Servir acompañado con huevo frito y plátano frito al lado."
        )
    )

    private val ingredients = recipes
        .flatMap { it.ingredients }
        .distinctBy { it.lowercase() }
        .toMutableList()

    fun getAllRecipes(): List<Recipe> = recipes

    fun getAllIngredients(): List<String> = ingredients

    fun addRecipe(recipe: Recipe) {
        val newId = if (recipes.isNotEmpty()) recipes.maxOf { it.id } + 1 else 1
        val newRecipe = recipe.copy(id = newId)
        recipes.add(newRecipe)
        recipe.ingredients.forEach { addIngredient(it) }
    }

    fun addIngredient(name: String) {
        val clean = name.trim()
        if (clean.isNotEmpty() && ingredients.none { it.equals(clean, true) }) {
            ingredients.add(clean)
        }
    }

    fun updateRecipe(updatedRecipe: Recipe) {
        val index = recipes.indexOfFirst { it.id == updatedRecipe.id }
        if (index != -1) {
            recipes[index] = updatedRecipe
        }
    }

    fun searchByIngredient(selected: Set<String>): List<Recipe> {
        if (selected.isEmpty()) return emptyList()
        return recipes.filter { recipe ->
            selected.all { it in recipe.ingredients }
        }
    }

    fun searchByRecipe(query: String): List<Recipe> {
        val cleanQuery = query.trim().lowercase()
        if (cleanQuery.isEmpty()) return emptyList()
        return recipes.filter { recipe ->
            recipe.name.lowercase().contains(cleanQuery)
        }
    }

    fun removeRecipe(recipe: Recipe) {
        recipes.removeIf { it.id == recipe.id }
    }

    fun clearAll() {
        recipes.clear()
        ingredients.clear()
    }
}