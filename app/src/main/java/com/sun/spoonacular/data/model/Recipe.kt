package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("readyInMinutes")
    @Expose
    val timeCook: String?,
    @SerializedName("spoonacularScore")
    @Expose
    val score: Double?,
    @SerializedName("image")
    @Expose
    val image: String?
) : Parcelable
