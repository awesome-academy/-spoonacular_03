package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("id")
    @Expose
    val id: Int? = 0,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("readyInMinutes")
    @Expose
    val timeCook: String? = null,
    @SerializedName("spoonacularScore")
    @Expose
    val score: Double? = null,
    @SerializedName("image")
    @Expose
    val image: String? = null
) : Parcelable
