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
    fun retrieveRecipeWithoutIngredientList(search:String): Flow<ApiResult<Results>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithoutIngredientList(search)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun retrieveRecipeWithIngredientList(search:String,includeIngredient:String="", excludeIngredient:String=""): Flow<ApiResult<Results>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_searchRecipeDataSource.getSearchWithIngredientList(search, includeIngredient,excludeIngredient)))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}

