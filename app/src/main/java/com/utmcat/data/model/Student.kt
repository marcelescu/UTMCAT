package com.utmcat.data.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("Password")
    val parola: String = ""
)