package com.example.whattoeat.ui.theme.screens.search


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.whattoeat.R
import com.example.whattoeat.core.Constants.KEY_SEARCH_RECIPE
import com.example.whattoeat.models.IngredientSearch
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Results
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.RecipeCard
import com.example.whattoeat.ui.theme.screens.search.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.search.components.TopNavigationButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay
import okhttp3.internal.wait

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel, navController: NavController
) {

    var currentSearch by rememberSaveable {
        mutableStateOf("")
    }
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }

    val listIncludeIngredient = rememberMutableStateListOf<IngredientSearch>()
    val listExcludeIngredient = rememberMutableStateListOf<IngredientSearch>()
    var leftScreen by remember {
        mutableStateOf(true)
    }


// TODO : FIX THE SIZE PICTURE THAT IS NOT THE SAME FROM the multiple instance of specificRecipeScreen and SpecificRandomRecipeScreen
// Reason is that url to the picture is not always the same. EX:
// https://img.spoonacular.com/recipes/664359-312x231.jpg
// https://img.spoonacular.com/recipes/664359-556x370.jpg
// Gotta try to find a way to change size or always get the good size
    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            // Control of the top navigation
            Row(
                Modifier
                    .fillMaxHeight(0.075f)
                    .fillMaxWidth()
                    .fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                TopNavigationButton(
                    text = stringResource(id = R.string.search),
                    modifier = Modifier.fillMaxWidth(0.5f),
                    leftScreenValue = leftScreen,
                    onMutableValueChange = { leftScreen = it })
                TopNavigationButton(
                    text = stringResource(id = R.string.filter),
                    modifier = Modifier.fillMaxWidth(),
                    leftScreenValue = !leftScreen,
                    onMutableValueChange = { leftScreen = !it })
            }
            Column(
                Modifier
                    .padding(12.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (leftScreen) {
                    SearchPage(
                        searchValue = searchValue,
                        onMutableSearchValueChange = { searchValue = it },
                        searchViewModel = searchViewModel,
                        navController = navController,
                        currentSearch = currentSearch,
                        onMutableCurrentSearchChange = { currentSearch = it },
                        listIncludeIngredient = listIncludeIngredient,
                        listExcludeIngredient = listExcludeIngredient
                    )
                } else {
                    Filter(
                        searchViewModel = searchViewModel,
                        listIncludeIngredient = listIncludeIngredient,
                        listExcludeIngredient = listExcludeIngredient,

                        )
                }
            }
        }
    }
}


//https://stackoverflow.com/questions/68885154/using-remembersaveable-with-mutablestatelistof


@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)

@Composable
fun SearchPage(
    searchValue: String,
    onMutableSearchValueChange: (String) -> Unit,
    searchViewModel: SearchViewModel,
    navController: NavController,
    currentSearch: String,
    onMutableCurrentSearchChange: (String) -> Unit,
    listIncludeIngredient: List<IngredientSearch>,
    listExcludeIngredient: List<IngredientSearch>
) {
    var listRecipe by remember {
        mutableStateOf<List<Recipe>>(listOf())
    }

    val searchUiState by searchViewModel.searchUiState.collectAsState()

    Column(
        Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onValueChange = { newText ->
                onMutableSearchValueChange(newText.trimStart { it == '0' })
            },
            singleLine = true,
            placeholder = { Text(text = stringResource(id = R.string.search)) },
            label = { Text(text = stringResource(R.string.search)) })

        Column(
            Modifier
                .fillMaxHeight(0.90f)

                .padding(0.dp, 12.dp, 0.dp, 0.dp)
        ) {
            if (listRecipe.isNotEmpty()) {
                // https://developer.android.com/quick-guides/content/lazily-load-list
                LazyColumn(
                    Modifier.fillMaxHeight(0.99f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listRecipe) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            navController = navController,
                            KEY_SEARCH_RECIPE
                        )
                    }
                }
            }

            when (val state = searchUiState) {

                is SearchUiState.Error -> Toast.makeText(
                    LocalContext.current, state.exception.message, Toast.LENGTH_LONG
                ).show()

                is SearchUiState.Loading -> {
                    if (currentSearch.isBlank() && listRecipe.isEmpty())
                        Text(
                            text = stringResource(R.string.no_recipe)
                        )
                    else if (!searchViewModel.isLoading!!) {
                        LinearProgressIndicator(
                            Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                is SearchUiState.Success -> {
                    listRecipe = state.recipes.results
                    if (listRecipe.isEmpty()) {
                        Text(text = stringResource(R.string.no_recipe_found))
                    }
                }
            }

        }

        Row(
            Modifier
                .height(48.dp)
                .padding(0.dp, 12.dp, 0.dp, 0.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
        ) {
            Button(
                onClick = {
                    onMutableCurrentSearchChange(searchValue);
                    if (listRecipe.isNotEmpty()) {
                        listRecipe = listOf()
                    }
                    search(
                        searchValue,
                        includeIngredient = transformToString(listIncludeIngredient.toMutableStateList()),
                        excludeIngredient = transformToString(listExcludeIngredient.toMutableStateList()),
                        searchViewModel,
                        newSearch = true
                    )
                }) {
                Text(text = stringResource(R.string.search))
            }
            if (currentSearch == searchValue && searchViewModel.recipes.offset + searchViewModel.recipes.number < searchViewModel.recipes.totalResults) {
                Button(
                    onClick = {

                        search(
                            searchValue,
                            includeIngredient = transformToString(
                                listIncludeIngredient.toMutableStateList()
                            ),
                            excludeIngredient = transformToString(
                                listExcludeIngredient.toMutableStateList()
                            ),
                            searchViewModel,
                            searchViewModel.recipes.offset + searchViewModel.recipes.number,
                            false
                        );


                    }) {
                    Text(text = stringResource(R.string.more))
                }
            }
        }
    }
}

fun transformToString(listIncludeIngredient: MutableList<IngredientSearch>): String {
    var stringForApi = ""
    listIncludeIngredient.forEach { ingredient ->
        stringForApi += ",${ingredient.name}"
    }
    return stringForApi
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    listIncludeIngredient: SnapshotStateList<IngredientSearch>,
    listExcludeIngredient: SnapshotStateList<IngredientSearch>,
    searchViewModel: SearchViewModel,
) {

    var includeIngredientExpanded by remember {
        mutableStateOf(false)
    }


    var currentIncludeIngredientValue by remember {
        mutableStateOf("")
    }

    var excludeIngredientExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var currentExcludeIngredientValue by remember {
        mutableStateOf("")
    }


// https://stackoverflow.com/questions/76039608/editable-dynamic-exposeddropdownmenubox-in-jetpack-compose

    AutoCompleteTextBoxWithItem(
        isIngredientSuggestionExpanded = includeIngredientExpanded,
        onMutableIsIngredientSuggestionExpandedChange = { includeIngredientExpanded = it },
        currentIngredientValue = currentIncludeIngredientValue,
        onMutableCurrentIngredientValueChange = { currentIncludeIngredientValue = it },
        listIngredient = listIncludeIngredient,
        searchViewModel = searchViewModel
    )

    AutoCompleteTextBoxWithItem(
        isIngredientSuggestionExpanded = excludeIngredientExpanded,
        onMutableIsIngredientSuggestionExpandedChange = { excludeIngredientExpanded = it },
        currentIngredientValue = currentExcludeIngredientValue,
        onMutableCurrentIngredientValueChange = { currentExcludeIngredientValue = it },
        listIngredient = listExcludeIngredient,
        searchViewModel = searchViewModel
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteTextBoxWithItem(
    isIngredientSuggestionExpanded: Boolean,
    onMutableIsIngredientSuggestionExpandedChange: (Boolean) -> Unit,
    currentIngredientValue: String,
    onMutableCurrentIngredientValueChange: (String) -> Unit,
    listIngredient: SnapshotStateList<IngredientSearch>,
    searchViewModel: SearchViewModel

) {
    ExposedDropdownMenuBox(
        expanded = isIngredientSuggestionExpanded,
        onExpandedChange = {
            onMutableIsIngredientSuggestionExpandedChange(!isIngredientSuggestionExpanded)
        }) {

        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = currentIngredientValue,
            singleLine = true,
            onValueChange = { newText ->
                onMutableCurrentIngredientValueChange(newText.trimStart { it == '0' })
            },
            label = { Text(text = stringResource(R.string.include_ingredients)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isIngredientSuggestionExpanded)
            }
        )


        LaunchedEffect(currentIngredientValue) {
            if (currentIngredientValue.isNotBlank())
                searchViewModel.searchIngredient(currentIngredientValue, true)

        }

        val searchIngredientUiState by searchViewModel.searchIngredientUiState.collectAsState()
        when (val state = searchIngredientUiState) {
            is SearchIngredientUiState.Error -> Toast.makeText(
                LocalContext.current, state.exception.message, Toast.LENGTH_LONG
            ).show()

            SearchIngredientUiState.Loading -> {}

            is SearchIngredientUiState.Success -> {

                if (state.ingredients.isNotEmpty()) {
                    DropdownMenu(modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .exposedDropdownSize(true),
                        properties = PopupProperties(focusable = false),
                        expanded = isIngredientSuggestionExpanded,
                        onDismissRequest = {
                            onMutableIsIngredientSuggestionExpandedChange(false)
                        }) {
                        state.ingredients.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(text = selectionOption.name) },
                                onClick = {
                                    onMutableCurrentIngredientValueChange("")
                                    listIngredient += selectionOption
                                    onMutableIsIngredientSuggestionExpandedChange(false)
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding

                            )
                        }
                    }
                }
            }

        }

    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(listIngredient.toList()) { ingredient ->
            IngredientCard(
                listIncludeIngredient = listIngredient,
                ingredient = ingredient
            )
        }

    }
}