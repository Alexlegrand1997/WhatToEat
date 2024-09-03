package com.example.whattoeat.core

import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes

object AlreadyLoadRandomRecipe {
    private var loadedRecipeState:Recipes = Recipes()

    fun setLoadedRecipeState(recipes: Recipes){
        loadedRecipeState=recipes
    }
    fun getLoadedRecipeState(): Recipes{
        return loadedRecipeState
    }
}

object CurrentSpecificRecipe {
    private var currentRecipeId:String="0"
    fun setRecipeId(id:String){
        currentRecipeId=id
    }
    fun getRecipeId():String{
        return currentRecipeId
    }

}

object CurrentSpecificRandomRecipe {
    private var currentRandomRecipe:Recipe=Recipe()
    fun setSpecificRandomRecipe(recipe: Recipe){
        currentRandomRecipe=recipe
    }
    fun getSpecificRandomRecipe():Recipe{
        return currentRandomRecipe
    }
}