package com.mdavila_2001.practicorecetasmarcelodavila.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier
) {
    NavigationBar(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF16FA2C),
                        Color(0xFF28E4FA)
                    ),
                    start = Offset(-500f, 0f),
                    end = Offset(500f, 0f)
                )
            ),
        containerColor = Color.Transparent,
    ) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = if (selectedTab == 0) Color.White else Color.Black
                )
            },
            label = {
                Text(
                    "Buscar",
                    color =Color.Black
                )
            }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = "Recetas",
                    tint = if (selectedTab == 1) Color.White else Color.Black
                )
            },
            label = {
                Text(
                    "Recetas",
                    color = Color.Black
                )
            }
        )
    }
}