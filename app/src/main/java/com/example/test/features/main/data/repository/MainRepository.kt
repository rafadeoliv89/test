package com.example.test.features.main.data.repository

import com.example.test.core.data.BaseResponse
import com.example.test.core.network.ResultWrapper
import com.example.test.features.main.data.response.ApiResponse

interface MainRepository {

    suspend fun getRepositories(page: Int, sort: String, q: String) : ResultWrapper<BaseResponse<List<ApiResponse>>>
}