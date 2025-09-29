package com.mdavila_2001.practicorecetasmarcelodavila.ui.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mdavila_2001.practicorecetasmarcelodavila.NavScreens
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.IngredientSelectScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeDetailScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeListScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.SearchResultsScreen
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavigationApp(
    navController: NavHostController = rememberNavController(),
    vm: RecipeViewmodel = RecipeViewmodel(),
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.INGREDIENTS
    ) {
        composable(NavScreens.INGREDIENTS.name) {
            IngredientSelectScreen(
                vm = vm,
                onSearch = {
                    navController.navigate(NavScreens.RECIPES.name)
                },
                modifier = Modifier
            )
        }

        composable(NavScreens.RECIPES.name) {
            RecipeListScreen(
                vm = vm,
                onRecipeClick = {
                    navController.navigate(NavScreens.DETAILS.name)
                },
                onRecipeAdd = {
                    navController.navigate(NavScreens.FORM.name)
                },
                modifier = Modifier
            )
        }

        composable(NavScreens.RESULTS.name) {
            SearchResultsScreen(
                vm = vm,
                onRecipeClick = {
                    navController.navigate(NavScreens.DETAILS.name)
                },
                onBackClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }

        composable(NavScreens.DETAILS.name) {
            vm.selectedRecipe?.let { recipe ->
                RecipeDetailScreen(
                    recipe = recipe,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
        }
    }
}