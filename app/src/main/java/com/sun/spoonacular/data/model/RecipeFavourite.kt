package com.sun.spoonacular.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sun.spoonacular.utils.Constant

@Entity(tableName = Constant.FAVOURITE_RECIPE_TABLE_NAME)
data class RecipeFavourite(
    @PrimaryKey
    val id: Int? = 0,
    val title: String? = null,
    val timeCook: String? = null,
    val score: Double? = null,
    val image: String? = null
)
