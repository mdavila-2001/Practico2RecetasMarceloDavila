package com.mdavila_2001.practicorecetasmarcelodavila.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdavila_2001.practicorecetasmarcelodavila.ui.components.IngredientChip
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.Recipe
import com.mdavila_2001.practicorecetasmarcelodavila.viewmodels.RecipeViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeFormScreen(
    vm: RecipeViewmodel,
    onSaved: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier
) {
    val editingRecipe = vm.selectedRecipe

    var name by remember { mutableStateOf(editingRecipe?.name ?: "") }
    var procedure by remember { mutableStateOf(editingRecipe?.procedure ?: "") }
    var newIngredient by remember { mutableStateOf("") }
    val selectedIngredients = remember {
        mutableListOf<String>().apply {
            editingRecipe?.ingredients?.forEach { add(it) }
        }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Text(
            "Seleccionar Ingredientes:",
            style = MaterialTheme.typography.bodyLarge
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            vm.ingredients.forEach { ing ->
                IngredientChip(
                    ingredient = ing,
                    isSelected = selectedIngredients.contains(ing),
                    onSelectionChange = { isSelected ->
                        if (isSelected) {
                            selectedIngredients.add(ing)
                        } else {
                            selectedIngredients.remove(ing)
                        }
                    },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = newIngredient,
                onValueChange = { newIngredient = it },
                label = { Text("Nuevo Ingrediente") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Button(
                onClick = {
                    if (newIngredient.isNotBlank() && !vm.ingredients.contains(newIngredient)) {
                        vm.addIngredient(newIngredient)
                        selectedIngredients.add(newIngredient)
                        newIngredient = ""
                    }
                }
            ) {
                Text("Agregar")
            }
        }

        OutlinedTextField(
            value = procedure,
            onValueChange = { procedure = it },
            label = { Text("Procedimiento") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 6
        )
        Row(
            modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    if(name.isNotBlank() && procedure.isNotBlank() && selectedIngredients.isNotEmpty()) {
                        if(editingRecipe == null) {
                            vm.addRecipe(
                                Recipe(
                                    id = 0,
                                    name = name,
                                    ingredients = selectedIngredients.toList(),
                                    procedure = procedure
                                )
                            )
                        } else {
                            vm.updateRecipe(
                                editingRecipe.copy(
                                    name = name,
                                    ingredients = selectedIngredients.toList(),
                                    procedure = procedure
                                )
                            )
                        }
                        vm.clearSelectedRecipe()
                        onSaved()
                    }
                },
                enabled = name.isNotBlank() && procedure.isNotBlank() && selectedIngredients.isNotEmpty()
            ) {
                Text("Guardar")
            }
            Button(
                onClick = {
                    vm.clearSelectedRecipe()
                    onCancel()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF4D4D),
                    contentColor = Color.White
                )
            ) {
                Text("Cancelar")
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun RecipeFormScreenPreview() {
    // ViewModel simulado con datos ficticios para previsualizar
    val fakeViewModel = remember { RecipeViewmodel() }
    RecipeFormScreen(
        vm = fakeViewModel,
        onSaved = { /* Simulamos que guardamos */ },
        onCancel = { /* Simulamos que cancelamos */ },
        modifier = Modifier
    )
}