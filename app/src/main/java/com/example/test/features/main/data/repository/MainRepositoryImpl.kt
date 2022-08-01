package com.example.test.features.main.data.repository

import com.example.test.core.data.BaseResponse
import com.example.test.core.data.RepositoriesDao
import com.example.test.core.network.ResultWrapper
import com.example.test.core.network.call
import com.example.test.features.main.data.MainService
import com.example.test.features.main.data.response.ApiResponse
import com.example.test.features.main.utils.ConvertUtil
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: MainService,
    private val repositoriesDao: RepositoriesDao?
) : MainRepository {

    override suspend fun getRepositories(
        page: Int,
        sort: String,
        q: String
    ): ResultWrapper<BaseResponse<List<ApiResponse>>> {

        val repositories = repositoriesDao?.getAllRepositories(page)
        if (repositories?.isEmpty() == true) {
            val result = service.getRepositoriesList(page, sort, q)
            repositoriesDao?.insertRepository(
                ConvertUtil.convertApiResponseToDb(
                    result.items ?: arrayListOf(),
                    page
                )
            )
            return call {
                result
            }
        } else {
            return ResultWrapper.Success(
                BaseResponse(
                    repositories?.count() ?: 0,
                    false,
                    ConvertUtil.convertDbToApiResponse(repositories ?: listOf())
                )
            )
        }

    }
}