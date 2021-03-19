package com.sun.spoonacular.data.source

import com.sun.spoonacular.data.source.remote.RemoteDataSource

class Repository private constructor(private val remote: DataSource.Remote) {

    suspend fun getRecipe() = remote.getRecipe()

    companion object {
        fun newInstance() = Repository(RemoteDataSource.newInstance())
    }
}
