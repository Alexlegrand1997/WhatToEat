package com.example.whattoeat.ui.theme.screens.search


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whattoeat.R
import com.example.whattoeat.core.Constants.KEY_SEARCH_RECIPE
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.RecipeCard

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navController: NavController
) {

    var currentSearch by rememberSaveable {
        mutableStateOf("")
    }
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    // TODO : FIX THE SIZE PICTURE THAT IS NOT THE SAME FROM the multiple instance of specificRecipeScreen and SpecificRandomRecipeScreen
    // Reason is that url to the picture is not always the same. EX:
    // https://img.spoonacular.com/recipes/664359-312x231.jpg
    // https://img.spoonacular.com/recipes/664359-556x370.jpg
    // Gotta try to find a way to change size or always get the good size
    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = searchValue,
                onValueChange = { newText ->
                    searchValue = newText.trimStart { it == '0' }
                },
                singleLine = true,
                placeholder = { Text(text = stringResource(id = R.string.search)) },
                label = { Text(text = stringResource(R.string.search)) }
            )

            val searchUiState by searchViewModel.searchUiState.collectAsState()
            when (val state = searchUiState) {
                is SearchUiState.Error -> Toast.makeText(
                    LocalContext.current, state.exception.message, Toast.LENGTH_LONG
                ).show()

                SearchUiState.Loading -> {
                    Text(text = stringResource(R.string.no_recipe))
                }

                is SearchUiState.Success ->
                    if (state.recipes.results.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxHeight(0.9f),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            items(state.recipes.results) { recipe ->
                                RecipeCard(recipe = recipe, navController = navController,KEY_SEARCH_RECIPE)
                            }
                        }
                    } else {
                        Text(text = stringResource(R.string.no_recipe_found))
                    }
            }

            Row(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Button(

                    onClick = {
                        currentSearch = searchValue
                        search(
                            searchValue,
                            "",
                            "",
                            searchViewModel,
                            newSearch = true
                        )
                    }) {
                    Text(text = stringResource(R.string.search))
                }
                if (currentSearch == searchValue && searchValue != "" && searchViewModel.recipes.offset + searchViewModel.recipes.number < searchViewModel.recipes.totalResults) {
                    Button(

                        onClick = {
                            search(
                                searchValue,
                                "",
                                "",
                                searchViewModel,
                                searchViewModel.recipes.offset + searchViewModel.recipes.number,
                                false
                            )
                        }) {
                        Text(text = stringResource(R.string.more))
                    }
                }
            }

        }
    }

}


fun search(
    search: String,
    includeIngredient: String,
    excludeIngredient: String,
    searchViewModel: SearchViewModel,
    offset: Int = 0,
    newSearch: Boolean = true
) {
    searchViewModel.search(search, includeIngredient, excludeIngredient, offset, newSearch)
}