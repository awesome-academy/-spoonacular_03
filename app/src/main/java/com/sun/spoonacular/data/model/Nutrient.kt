package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nutrient(
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("amount")
    @Expose
    val amount: Double?,
    @SerializedName("unit")
    @Expose
    val unit: String?
) : Parcelable
