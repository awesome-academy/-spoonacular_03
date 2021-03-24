package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutrientResponse(
    @SerializedName("nutrients")
    @Expose
    val nutrients: MutableList<Nutrient>?
) : Parcelable
