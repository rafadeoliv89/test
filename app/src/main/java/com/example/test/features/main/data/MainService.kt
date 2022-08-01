package com.example.test.features.main.data

import com.example.test.core.data.BaseResponse
import com.example.test.features.main.data.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("search/repositories")
    suspend fun getRepositoriesList(
        @Query("page") page: Int,
        @Query("sort") sort: String,
        @Query("q") search: String,
    ): BaseResponse<List<ApiResponse>>
}