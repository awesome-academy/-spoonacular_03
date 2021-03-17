package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(
    @SerializedName("recipes")
    @Expose
    val recipes: List<Recipe>
) : Parcelable
