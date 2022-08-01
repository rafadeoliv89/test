package com.example.test.features.main.utils

import com.example.test.core.data.Repositories
import com.example.test.features.main.data.response.ApiOwner
import com.example.test.features.main.data.response.ApiResponse

object ConvertUtil {

    fun convertApiResponseToDb(response: List<ApiResponse>, page: Int): List<Repositories> {
        return response.map {
            Repositories(
                name = it.name,
                starsCount = it.starsCount,
                forksCount = it.forksCount,
                apiOwner = ApiOwner(
                    login = it.apiOwner?.login.toString(),
                    avatar = it.apiOwner?.avatar.toString()
                ),
                page = page
            )
        }
    }

    fun convertDbToApiResponse(db: List<Repositories>): List<ApiResponse> {
        return db.map {
            ApiResponse(
                name = it.name,
                starsCount = it.starsCount,
                forksCount = it.forksCount,
                apiOwner = ApiOwner(
                    login = it.apiOwner.login,
                    avatar = it.apiOwner.avatar
                )
            )
        }
    }
}