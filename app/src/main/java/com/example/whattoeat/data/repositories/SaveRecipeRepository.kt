package com.example.whattoeat.data.repositories


import android.provider.ContactsContract.Data
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.entities.SaveRecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onErrorReturn


// Video use to make the repository with Room database
//https://www.youtube.com/watch?v=7cEqDV_c94k

class SaveRecipeRepository (private val recipeDao: RecipeDao) {


    fun getAllSaveRecipe(): Flow<List<SaveRecipeEntity>> {
        return recipeDao.getAll()
    }

//    fun getAllSaveRecipe(): Flow<DataStoreResult<List<SaveRecipeEntity>>> {
//       return flow{
//           emit(DataStoreResult.Loading)
//           try{
//               emit(DataStoreResult.Success(recipeDao.getAll()))
//           }
//           catch (ex:Exception){
//               emit(DataStoreResult.Error(ex))
//           }
//       }.flowOn(Dispatchers.IO)
//    }

    // TODO HAVE TO AJUST IF ERROR
    fun getOneSaveRecipe(idRecipe: Int): Flow<SaveRecipeEntity>{
        return recipeDao.getOne(idRecipe)
    }

    fun isSaveRecipe(idRecipe: Int):Flow<Boolean>{
        return recipeDao.isSaveRecipe(idRecipe)
    }


    suspend fun insertOneRecipe(saveRecipeEntity: SaveRecipeEntity){
        recipeDao.insert(saveRecipeEntity)
    }

    suspend fun deleteOneSaveRecipe(idRecipe: Int){
        recipeDao.delete(idRecipe)
    }

}