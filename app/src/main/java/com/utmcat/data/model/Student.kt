package com.utmcat.data.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("Grupa")
    val grupa: String = "",
    @SerializedName("Nume")
    val nume: String = "",
    @SerializedName("Note")
    val note: ArrayList<Nota>
)