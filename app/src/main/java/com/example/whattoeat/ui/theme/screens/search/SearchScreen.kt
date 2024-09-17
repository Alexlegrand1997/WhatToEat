package com.example.whattoeat.ui.theme.screens.search


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whattoeat.R
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.RecipeCard

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navController: NavController
) {


    var searchValue by remember {
        mutableStateOf("")
    }
//    var recipes by remember {
//        mutableStateOf(searchViewModel.recipes.recipes)
//    }


    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = searchValue,
                onValueChange = { searchValue = it },
                label = { Text(text = stringResource(R.string.search)) }
            )

            val searchUiState by searchViewModel.searchUiState.collectAsState()
            when (val state = searchUiState) {
                is SearchUiState.Error -> Toast.makeText(
                    LocalContext.current, state.exception.message, Toast.LENGTH_LONG
                ).show()

                SearchUiState.Loading -> {
                    Text(text = "No Recipe")
                }

                is SearchUiState.Success -> LazyColumn(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.recipes.results) { recipe ->
                        RecipeCard(recipe = recipe, navController = navController)
                    }
                }
            }


            Row(Modifier.fillMaxHeight()) {
                Button(onClick = { search(searchValue, "", "", searchViewModel) }) {
                    Text(text = stringResource(R.string.search))
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "More")
                }
            }
        }
    }

}


fun search(
    search: String,
    includeIngredient: String,
    excludeIngredient: String,
    searchViewModel: SearchViewModel
) {
    searchViewModel.search(search, includeIngredient, excludeIngredient)
}