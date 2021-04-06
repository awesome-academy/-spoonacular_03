package com.sun.spoonacular.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.sun.spoonacular.data.model.RecipeFavourite
import com.sun.spoonacular.data.source.DataSource

class LocalDataSource private constructor(private val favouriteDB: FavouriteDB) : DataSource.Local {

    override fun getRecipe(): LiveData<List<RecipeFavourite>> {
        return favouriteDB.favouriteDAO().getAllRecipe()
    }

    override suspend fun insertRecipe(recipe: RecipeFavourite) {
        return favouriteDB.favouriteDAO().insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(idRecipe: Int) {
        return favouriteDB.favouriteDAO().deleteRecipe(idRecipe)
    }

    override suspend fun findAlreadyExists(idRecipe: Int): List<RecipeFavourite> {
        return favouriteDB.favouriteDAO().findAlreadyExists(idRecipe)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(context: Context) =
            instance ?: DatabaseBuilder.getInstance(context)
                ?.let { it -> LocalDataSource(it).also { instance = it } }
    }
}
