package com.mdavila_2001.practicorecetasmarcelodavila.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mdavila_2001.practicorecetasmarcelodavila.repositories.RecipeRepository

data class Recipe (
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val procedure: String
)

class RecipeViewmodel : ViewModel() {
    var recipes by mutableStateOf(RecipeRepository.getAllRecipes())
        private set

    var ingredients by mutableStateOf(RecipeRepository.getAllIngredients())
        private set

    var selectedIngredients by mutableStateOf(setOf<String>())
        private set

    var searchResults by mutableStateOf(listOf<Recipe>())
        private set

    var selectedRecipe by mutableStateOf<Recipe?>(null)
        internal set

    fun toggleIngredientSelection(ingredient: String, isSelected: Boolean) {
        selectedIngredients = if (isSelected) {
            selectedIngredients + ingredient
        } else {
            selectedIngredients - ingredient
        }
    }

    fun addRecipe(recipe: Recipe) {
        RecipeRepository.addRecipe(recipe)
        refresh()
    }

    fun updateRecipe(recipe: Recipe) {
        RecipeRepository.updateRecipe(recipe)
        refresh() // refrescamos la lista para que los cambios se reflejen
    }

    fun addIngredient(name: String) {
        RecipeRepository.addIngredient(name)
        refresh()
    }

    fun searchBySelectedIngredients() {
        searchResults = RecipeRepository.searchByIngredient(selectedIngredients)
    }

    fun searchByRecipeName(query: String) {
        searchResults = RecipeRepository.searchByRecipe(query)
    }

    fun selectRecipeById(recipe: Recipe) {
        selectedRecipe = recipe
    }

    fun clearSelectedRecipe() {
        selectedRecipe = null
    }

    fun deleteRecipe(recipe: Recipe) {
        RecipeRepository.removeRecipe(recipe)
        refresh()

        if (selectedRecipe?.id == recipe.id) {
            selectedRecipe = null
        }
    }

    private fun refresh() {
        recipes = RecipeRepository.getAllRecipes()
        ingredients = RecipeRepository.getAllIngredients()
    }
}