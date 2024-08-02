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

object CurrentSpecificRecipe {
    private var currentRecipeId:String="0"
    fun setRecipeId(id:String){
        currentRecipeId=id
    }
    fun getRecipeId():String{
        return currentRecipeId
    }

}