package com.example.test.core.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (

    @SerializedName("total_count")
    var totalCount: Int = 0,

    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,

    @SerializedName("items")
    var items: T? = null
)