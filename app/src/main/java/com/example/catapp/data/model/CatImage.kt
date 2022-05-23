package com.example.catapp.data.model

import com.google.gson.annotations.SerializedName

data class CatImage (
    @SerializedName("url")
    var url: String?
)