package com.utmcat.data.model

import com.google.gson.annotations.SerializedName

data class Nota(
    @SerializedName("Materie")
    val materie: String = "",
    @SerializedName("Nota")
    val nota: String = ""
)