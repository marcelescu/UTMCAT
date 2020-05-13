package com.utmcat.data.model

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("Note")
    val note: List<Nota>
)