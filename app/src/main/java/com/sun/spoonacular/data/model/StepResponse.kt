package com.sun.spoonacular.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepResponse(
    @SerializedName("steps")
    @Expose
    val steps: MutableList<Step>?
) : Parcelable
