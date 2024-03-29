package com.example.android.marsrealestate.network

import com.squareup.moshi.Json

data class UserProperty(
        val id: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        @Json(name = "gendr") val type: String
)