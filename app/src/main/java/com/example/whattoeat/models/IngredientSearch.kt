package com.example.whattoeat.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


// https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/Saver
// https://stackoverflow.com/questions/71126279/getting-crash-due-to-check-failed-in-jetpack-compose-android
// https://xuanlocle.medium.com/should-we-use-parcelable-in-kotlin-ab89c1422ffc
@Serializable
@Parcelize
data class IngredientSearch(
    val name: String = "",
    val image: String = ""
) :Parcelable { // Parcelable to be able to made it a mutableList

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
    }
}