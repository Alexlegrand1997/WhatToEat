package com.example.whattoeat.core

class CalculatedConstant {

    fun UrlSpecificRecipe(id:String): String{
        return "https://api.spoonacular.com/recipes/${id}/information"
    }
}