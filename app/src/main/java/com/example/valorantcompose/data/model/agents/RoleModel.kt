package com.example.valorantcompose.data.model.agents


import com.google.gson.annotations.SerializedName

data class RoleModel(
    @SerializedName("assetPath")
    val assetPath: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("displayIcon")
    val displayIcon: String? = "",
    @SerializedName("displayName")
    val displayName: String? = "",
    @SerializedName("uuid")
    val uuid: String? = ""
)