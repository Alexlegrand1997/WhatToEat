package com.example.whattoeat.core

object Constants {
    const val RANDOM_RECIPE="https://api.spoonacular.com/recipes/random"
    const val URL_CDN_INGREDIENT="https://img.spoonacular.com/ingredients_100x100/"


    const val SAVE_RECIPE_DB="save_recipe_table"

}

fun UrlSpecificRecipe(id:Int): String{
    return "https://api.spoonacular.com/recipes/${id}/information"
}