package com.example.valorantcompose.data.model.agents


import com.google.gson.annotations.SerializedName

data class AbilityModel(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("displayIcon")
    val displayIcon: String? = "",
    @SerializedName("displayName")
    val displayName: String? = "",
    @SerializedName("slot")
    val slot: String? = ""
)