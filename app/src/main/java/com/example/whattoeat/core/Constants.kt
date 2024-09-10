package com.example.whattoeat.core

import androidx.compose.ui.platform.LocalDensity

object Constants {
    const val RANDOM_RECIPE="https://api.spoonacular.com/recipes/random"
    const val URL_CDN_INGREDIENT="https://img.spoonacular.com/ingredients_100x100/"

    const val SAVE_RECIPE_DB="save_recipe_table"
    const val SETTING_DATASTORE ="settingDataStore"


    const val THEME_KEY ="theme"
    const val INGREDIENT_UNIT_KEY="ingredient_unit"
    const val USERNAME_KEY="username"
    const val POINT_LEFT_KEY="points_left"

    const val COST_POINT_SAVE_RECIPE=1.01
    const val COST_POINT_RANDOM_RECIPE_REFRESH=1.10

}

fun UrlSpecificRecipe(id:Int): String{
    return "https://api.spoonacular.com/recipes/${id}/information"
}