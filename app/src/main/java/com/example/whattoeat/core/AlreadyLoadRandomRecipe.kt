package com.example.whattoeat.core

object AlreadyLoadRandomRecipe {
    private var loadedRecipeState:Boolean=false

    fun setLoadedRecipeState(boolean: Boolean){
        loadedRecipeState=boolean
    }
    fun getLoadedRecipeState(): Boolean{
        return loadedRecipeState
    }
}