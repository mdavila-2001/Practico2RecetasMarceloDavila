package com.mdavila_2001.practicorecetasmarcelodavila.ui.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.mdavila_2001.practicorecetasmarcelodavila.NavScreens
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.IngredientSelectScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeDetailScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeFormScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.RecipeListScreen
import com.mdavila_2001.practicorecetasmarcelodavila.ui.screens.SearchResultsScreen
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

@SuppressLint("ViewModelConstructorInComposable")
// En este archivo, en la función NavigationApp
@Composable
fun NavigationApp(
    vm: RecipeViewmodel = RecipeViewmodel(),
    selectedTab: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = if (selectedTab == 0) NavScreens.INGREDIENTS.name else NavScreens.RECIPES.name,
    ) {
        composable(NavScreens.INGREDIENTS.name) {
            IngredientSelectScreen(
                vm = vm,
                onSearch = {
                    navController.navigate(NavScreens.RESULTS.name)
                },
                modifier = Modifier
            )
        }

        composable(NavScreens.RECIPES.name) {
            RecipeListScreen(
                vm = vm,
                onRecipeClick = { recipe ->
                    vm.selectedRecipe = recipe
                    navController.navigate(NavScreens.DETAILS.name)
                },
                onRecipeAdd = {
                    vm.clearSelectedRecipe()
                    navController.navigate(NavScreens.FORM.name)
                },
                modifier = Modifier
            )
        }

        composable(NavScreens.RESULTS.name) {
            SearchResultsScreen(
                vm = vm,
                onRecipeClick = { recipe ->
                    vm.selectedRecipe = recipe
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
                    onEdit = {
                        navController.navigate(NavScreens.FORM.name)
                    },
                    onDelete = { r ->
                        vm.deleteRecipe(r)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
        }

        composable(NavScreens.FORM.name) {
            RecipeFormScreen(
                vm = vm,
                onSaved = {
                    navController.popBackStack(NavScreens.DETAILS.name, true)
                    navController.navigate(NavScreens.RECIPES.name)
                },
                onCancel = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    }
}
