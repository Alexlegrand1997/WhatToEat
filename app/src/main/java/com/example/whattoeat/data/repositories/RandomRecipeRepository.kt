package com.example.whattoeat.data.repositories

import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.datasources.RandomRecipeDataSource
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RandomRecipeRepository {

    private val _randomRecipeDataSource = RandomRecipeDataSource()

    fun retrieveOne(): Flow<ApiResult<Recipes>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(ApiResult.Success(_randomRecipeDataSource.getOne()))
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}