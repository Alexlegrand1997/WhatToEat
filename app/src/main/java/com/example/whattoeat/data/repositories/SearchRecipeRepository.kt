package com.example.whattoeat.data.repositories

import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.datasources.SearchRecipeDataSource
import com.example.whattoeat.models.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRecipeRepository @Inject constructor(
    private val _searchRecipeDataSource: SearchRecipeDataSource
) {
    fun retrieveRecipeWithoutIngredientList(): Flow<ApiResult<Recipes>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithoutIngredientList()))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveRecipeWithIngredientList(includeIngredient:String="", excludeIngredient:String=""): Flow<ApiResult<Recipes>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithIngredientList(includeIngredient,excludeIngredient)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}

