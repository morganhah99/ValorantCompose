package com.example.valorantcompose.data.model.agents


import com.google.gson.annotations.SerializedName

data class AgentModel(
    @SerializedName("data")
    val `data`: List<DataModel?>? = listOf(),
    @SerializedName("status")
    val status: Int? = 0
)