package com.example.test.core.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.features.main.data.response.ApiOwner

@Entity(tableName = "repositories")
data class Repositories(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var name: String,
    var starsCount: Int,
    var forksCount: Int,
    @Embedded
    var apiOwner: ApiOwner,
    var page: Int
)
