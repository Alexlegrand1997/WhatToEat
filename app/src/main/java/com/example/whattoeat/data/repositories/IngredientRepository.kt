package com.example.whattoeat.data.repositories

import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.datasources.IngredientDataSource
import com.example.whattoeat.models.IngredientSearch
import com.example.whattoeat.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val _ingredientDataSource: IngredientDataSource
) {

    fun getMultipleIngredientSuggestion(ingredient: String): Flow<ApiResult<List<IngredientSearch>>> {
        return flow {
            emit(ApiResult.Loading)
            try {
                emit(
                    ApiResult.Success(
                        _ingredientDataSource.getMultipleIngredientSuggestion(
                            ingredient
                        )
                    )
                )
            } catch (ex: Exception) {
                emit(ApiResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}