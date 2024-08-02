package com.example.whattoeat.data.repositories

import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.datasources.SpecificRecipeDataSource
import com.example.whattoeat.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SpecificRecipeRepository {

    private val _specificRecipeDataSource= SpecificRecipeDataSource()

    fun getOneRecipe(idRecipe :String): Flow<ApiResult<Recipe>> {
        return flow{
            emit(ApiResult.Loading)
            try{
                emit(ApiResult.Success(_specificRecipeDataSource.getOneSpecificRecipe(idRecipe)))
            }
            catch (ex:Exception){
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}