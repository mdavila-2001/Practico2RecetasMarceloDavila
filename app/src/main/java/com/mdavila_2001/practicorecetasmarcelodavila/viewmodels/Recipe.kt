package com.mdavila_2001.practicorecetasmarcelodavila.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mdavila_2001.practicorecetasmarcelodavila.RecipeRepository

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

    private fun refresh() {
        recipes = RecipeRepository.getAllRecipes()
        ingredients = RecipeRepository.getAllIngredients()
    }
}