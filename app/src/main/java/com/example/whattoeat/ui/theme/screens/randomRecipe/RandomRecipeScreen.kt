package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.whattoeat.R
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.ui.theme.composables.LoadingSpinner

@Composable
fun RandomRecipeScreen(
    navController: NavHostController = rememberNavController(),
    randomRecipeViewModel: RandomRecipeViewModel = viewModel()
) {
    val randomRecipeUIState by randomRecipeViewModel.randomRecipeUIState.collectAsState()

    when (val state = randomRecipeUIState) {
        is RandomRecipeUIState.Error -> Toast.makeText(
            LocalContext.current,
            state.exception.message,
            Toast.LENGTH_LONG
        ).show()

        RandomRecipeUIState.Loading -> LoadingSpinner()
        is RandomRecipeUIState.Success -> {
            RandomRecipeScreenCard(state.recipes, randomRecipeViewModel)
        }
    }

}

@Composable
fun RandomRecipeScreenCard(recipes: Recipes, randomRecipeViewModel: RandomRecipeViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 20.dp)
        ) {
            SubcomposeAsyncImage(
                model =recipes.recipes[0].image,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(0.9f).
//                aspectRatio(16/9f).
                    clip(RoundedCornerShape(20.dp)),
                contentDescription = recipes.recipes[0].title,
                loading = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.height(225.dp)){
                        CircularProgressIndicator(modifier = Modifier.size(60.dp))
                    }
                }, error = {
                    Image(
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = stringResource(id = R.string.picture_unavailable)
                    )
                }
            )
            Text(text = recipes.recipes[0].title)
        }



        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            Button(onClick = { refreshRecipe(randomRecipeViewModel) }) {
                Text(text = "New Recipe")
            }
        }

    }
}

fun refreshRecipe(randomRecipeViewModel: RandomRecipeViewModel) {
    randomRecipeViewModel.getRandomRecipe()
}

