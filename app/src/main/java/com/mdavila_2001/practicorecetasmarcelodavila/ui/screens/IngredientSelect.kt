package com.mdavila_2001.practicorecetasmarcelodavila.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.IngredientChip
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IngredientSelectScreen(
    vm: RecipeViewmodel,
    onSearch: () -> Unit,
    modifier: Modifier
) {
    val ingredients by remember {
        mutableStateOf(vm.ingredients)
    }
    val selectedIngredients by remember {
        mutableStateOf(vm.selectedIngredients)
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    vm.searchBySelectedIngredients()
                    onSearch()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Buscar Recetas")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Seleccioná los ingredientes que tenés:",
                style = MaterialTheme.typography.titleMedium
            )
            FlowRow(
                modifier = Modifier.fillMaxSize()
            ) {
                vm.ingredients.forEach { ingredient ->
                    IngredientChip(
                        ingredient = ingredient,
                        isSelected = ingredient in vm.selectedIngredients,
                        onSelectionChange = { isSelected ->
                            vm.toggleIngredientSelection(ingredient, isSelected)
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientSelectScreenPreview() {
    IngredientSelectScreen(
        vm = RecipeViewmodel(),
        onSearch = {},
        modifier = Modifier
    )
}