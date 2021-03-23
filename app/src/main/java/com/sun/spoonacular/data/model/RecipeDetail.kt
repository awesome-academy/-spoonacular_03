package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDetail(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("readyInMinutes")
    @Expose
    val timeCook: Double?,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("extendedIngredients")
    @Expose
    val ingredient: ArrayList<Ingredient>?,
    @SerializedName("nutrition")
    @Expose
    val nutrients: NutrientResponse?,
    @SerializedName("analyzedInstructions")
    @Expose
    val step: MutableList<StepResponse>?,
    @SerializedName("spoonacularScore")
    @Expose
    val score: String?
) : Parcelable
