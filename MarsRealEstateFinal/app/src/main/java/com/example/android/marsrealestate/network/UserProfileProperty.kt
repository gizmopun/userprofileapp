package com.example.android.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfileProperty (
        val id: String,
        val first_name: String,
        val last_name: String,
        val email: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        @Json(name = "gendr") val type: String,
        val price: Double) : Parcelable {
        val isRental
        get() = type == "rent"
}
