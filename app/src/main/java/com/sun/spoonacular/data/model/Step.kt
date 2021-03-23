package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Step(
    @SerializedName("number")
    @Expose
    val number: Int?,
    @SerializedName("step")
    @Expose
    val step: String?
) : Parcelable
