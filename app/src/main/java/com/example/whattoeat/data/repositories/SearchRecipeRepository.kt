package com.example.whattoeat.data.repositories

import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.datasources.SearchRecipeDataSource
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRecipeRepository @Inject constructor(
    private val _searchRecipeDataSource: SearchRecipeDataSource
) {
    fun retrieveRecipeWithoutIngredientList(search:String,offset:Int): Flow<ApiResult<Results>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithoutIngredientList(search,offset)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveRecipeWithIngredientList(search:String,includeIngredient:String="", excludeIngredient:String="",offset:Int): Flow<ApiResult<Results>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithIngredientList(search, includeIngredient,excludeIngredient,offset)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}

