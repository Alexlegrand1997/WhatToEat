package com.example.whattoeat.core

import androidx.compose.ui.platform.LocalDensity

object Constants {
    const val RANDOM_RECIPE = "https://api.spoonacular.com/recipes/random"
    const val URL_CDN_INGREDIENT = "https://img.spoonacular.com/ingredients_100x100/"
    const val SEARCH_RECIPE = "https://api.spoonacular.com/recipes/complexSearch"

    const val SAVE_RECIPE_DB = "save_recipe_table"
    const val SETTING_DATASTORE = "settingDataStore"


    const val THEME_KEY = "theme"
    const val INGREDIENT_UNIT_KEY = "ingredient_unit"
    const val USERNAME_KEY = "username"
    const val POINT_LEFT_KEY = "points_left"

    const val COST_POINT_SAVE_RECIPE = 1.01
    const val COST_POINT_RANDOM_RECIPE_REFRESH = 1.10
    const val COST_POINT_SEARCH_RECIPE = 1.60

    const val DEFAULT_THEME_VALUE = 2 // 0 =Light mode ,  1 = dark mode , 2 = system mode
    const val DEFAULT_INGREDIENT_UNIT_VALUE = 0 // 0 = Metric ,  1 = US

    const val KEY_RANDOM_RECIPE = "RandomRecipeKey"
    const val KEY_SEARCH_RECIPE = "SearchRecipeKey"

}

fun UrlSpecificRecipe(id: Int): String {
    return "https://api.spoonacular.com/recipes/${id}/information"
}