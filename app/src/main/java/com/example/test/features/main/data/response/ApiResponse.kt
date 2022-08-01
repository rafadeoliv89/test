package com.example.test.features.main.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(

    @SerializedName("name")
    val name: String,

    @SerializedName("stargazers_count")
    val starsCount: Int,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("owner")
    val apiOwner: ApiOwner?
)

data class ApiOwner(

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar: String
)
