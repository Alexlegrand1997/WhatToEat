package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

import coil.compose.SubcomposeAsyncImage

import com.example.whattoeat.R
import com.example.whattoeat.core.Constants
import com.example.whattoeat.models.ExtendedIngredient
import com.example.whattoeat.models.Recipe

import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Step
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun RandomRecipeScreen(
    randomRecipeViewModel: RandomRecipeViewModel
) {
    val randomRecipeUIState by randomRecipeViewModel.randomRecipeUIState.collectAsState()

    when (val state = randomRecipeUIState) {
        is RandomRecipeUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        RandomRecipeUIState.Loading -> LoadingSpinner()
        is RandomRecipeUIState.Success -> {
            RandomRecipeScreenCard(state.recipes, randomRecipeViewModel)
        }
    }

}

@Composable
fun RandomRecipeScreenCard(recipes: Recipes, randomRecipeViewModel: RandomRecipeViewModel) {
    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                SubcomposeAsyncImage(model = recipes.recipes[0].image,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(20.dp)),
                    contentDescription = recipes.recipes[0].title,
                    loading = {
                        Box(
                            contentAlignment = Alignment.Center, modifier = Modifier.height(225.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(60.dp))
                        }
                    },
                    error = {
                        Image(
                            painter = painterResource(id = R.drawable.placeholder),
                            contentDescription = stringResource(id = R.string.picture_unavailable)
                        )
                    })
                Text(text = recipes.recipes[0].title)
            }

            val items = remember {
                listOf("Metric", "US")
            }
            var selectedIndex by remember {
                mutableStateOf(0)
            }

            TextSwitch(selectedIndex = selectedIndex, items = items, onSelectionChange = {
                selectedIndex = it
            })

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(recipes.recipes[0].extendedIngredients) { ingredient ->
                    ingredientCard(ingredient, selectedIndex)
                }
            }


            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { currentRecipeInfo.value = true },
                    Modifier
                        .padding(start = 4.dp)
                        .fillMaxWidth(1 / 3f)
                ) {
                    Text("Instruction")
                }
                // Context use for the ROOM db
                val context = LocalContext.current
                //TODO : Have to change state of button to save or removeFromSave if the recipe is in the save list of the user
                Button(
//                    onClick = { saveRecipe(context, recipes.recipes[0]) },
                    onClick = { saveRecipe(randomRecipeViewModel, recipes.recipes[0]) },
                    Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Text("Save")
                }
                Button(
                    onClick = { refreshRecipe(randomRecipeViewModel) },
                    Modifier
                        .padding(end = 4.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "New Recipe")
                }
            }

            if (currentRecipeInfo.value) {
                InstructionInfo(
                    onDismissRequest = {},
                    recipes.recipes[0].analyzedInstructions[0].steps,
                    currentRecipeInfo
                )
            }
        }
    }
}

private fun refreshRecipe(randomRecipeViewModel: RandomRecipeViewModel) {
    randomRecipeViewModel.getRandomRecipe()
}

private fun saveRecipe(randomRecipeViewModel: RandomRecipeViewModel,recipe: Recipe) {


   randomRecipeViewModel.saveRecipe(recipe)
//    GlobalScope.launch(Dispatchers.IO) {
//        recipeDao.insert(recipeToSave)
//        val testRecipe: SaveRecipeUserDao.RecipeSave = recipeDao.getOne(recipe.id)
//        withContext(Dispatchers.Main) {
//            Toast.makeText(
//                context, testRecipe.title, Toast.LENGTH_LONG
//            ).show()
//        }
//    }
}


//private fun saveRecipe(context: Context, recipe: Recipe) {
//    val db = Room.databaseBuilder(
//        context,
//        SaveRecipeUserDao.AppDatabase::class.java, "saveRecipe.db"
//    ).build()
//    val recipeDao = db.recipeDao()
//
//    val recipeToSave: SaveRecipeUserDao.RecipeSave = SaveRecipeUserDao.RecipeSave()
//
//    recipeToSave.id = recipe.id
//    recipeToSave.title = recipe.title
//    recipeToSave.image = recipe.image
//
//    GlobalScope.launch(Dispatchers.IO) {
//        recipeDao.insert(recipeToSave)
//        val testRecipe: SaveRecipeUserDao.RecipeSave = recipeDao.getOne(recipe.id)
//        withContext(Dispatchers.Main) {
//            Toast.makeText(
//                context, testRecipe.title, Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//}


@Composable
fun ingredientCard(ingredient: ExtendedIngredient, typeMeasure: Int) {
    Card(
        Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.height(75.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.fillMaxWidth(0.3f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loadImageIngredient(ingredient)

            }

            Column(Modifier.padding(start = 8.dp)) {
                Text(ingredient.name)
                // True is US
                if (typeMeasure == 1) {
                    Text("${ingredient.measures.us.amount} ${ingredient.measures.us.unitLong}")
                }
                // False is Metric
                else {
                    Text("${ingredient.measures.metric.amount} ${ingredient.measures.metric.unitLong}")

                }
            }
        }
    }
}

@Composable
fun loadImageIngredient(ingredient: ExtendedIngredient) {
    SubcomposeAsyncImage(model = Constants.URL_CDN_INGREDIENT + ingredient.image,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxHeight(0.8f),
        contentDescription = ingredient.name,
        filterQuality = FilterQuality.High,
        loading = {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.height(225.dp)
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        },
        error = {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(id = R.string.picture_unavailable)
            )
        })
}


// Try to make a nice switch
// Reference : https://stackoverflow.com/questions/77330319/how-to-create-switcher-with-two-string-states
fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

@Composable
private fun TextSwitch(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    items: List<String>,
    onSelectionChange: (Int) -> Unit
) {

    BoxWithConstraints(
        modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xfff3f3f2))
            .padding(8.dp)
    ) {
        if (items.isNotEmpty()) {

            val maxWidth = this.maxWidth
            val tabWidth = maxWidth / items.size

            val indicatorOffset by animateDpAsState(
                targetValue = tabWidth * selectedIndex,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )

            // This is for shadow layer matching white background
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .shadow(4.dp, RoundedCornerShape(8.dp))
                    .width(tabWidth)
                    .fillMaxHeight()
            )

            Row(modifier = Modifier
                .fillMaxWidth()

                .drawWithContent {

                    // This is for setting black tex while drawing on white background
                    val padding = 8.dp.toPx()
                    drawRoundRect(
                        topLeft = Offset(x = indicatorOffset.toPx() + padding, padding),
                        size = androidx.compose.ui.geometry.Size(
                            size.width / 2 - padding * 2, size.height - padding * 2
                        ),
                        color = Color.Black,
                        cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                    )

                    drawWithLayer {
                        drawContent()

                        // This is white top rounded rectangle
                        drawRoundRect(
                            topLeft = Offset(x = indicatorOffset.toPx(), 0f),
                            size = androidx.compose.ui.geometry.Size(
                                size.width / 2, size.height
                            ),
                            color = Color.White,
                            cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                            blendMode = BlendMode.SrcOut
                        )
                    }

                }) {
                items.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .width(tabWidth)
                            .fillMaxHeight()
                            .clickable(interactionSource = remember {
                                MutableInteractionSource()
                            }, indication = null, onClick = {
                                onSelectionChange(index)
                            }), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text, fontSize = 20.sp, color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InstructionInfo(
    onDismissRequest: () -> Unit, steps: List<Step>, currentRecipeInfo: MutableState<Boolean>
) {
    Dialog(onDismissRequest = { onDismissRequest() }

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                horizontalArrangement = Arrangement.Center,
            ) {
                if (steps.isNotEmpty()) {
                    LazyColumn(
                        Modifier.fillMaxHeight(0.9f),
                    ) {
                        items(steps) { step ->
                            Row(
                                Modifier.padding(top = 8.dp, end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    Modifier.fillMaxWidth(0.08f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(step.number.toString())
                                }
                                StepInstructionCard(step)
                            }
                        }
                    }
                } else {
                    Text(stringResource(R.string.no_instruction))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.Center,

                ) {
                OutlinedButton(
                    onClick = { onDismissRequest(); currentRecipeInfo.value = false },
                    modifier = Modifier.padding(8.dp),
                ) {

                    Text(
                        stringResource(R.string.close), style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }


            }
        }
    }

}

@Composable
fun StepInstructionCard(step: Step) {
    Card(
        Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Text(text = step.step, Modifier.padding(4.dp))
    }
}

