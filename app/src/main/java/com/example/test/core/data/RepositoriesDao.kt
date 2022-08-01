package com.example.test.core.data

import androidx.room.*

@Dao
interface RepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repositories: List<Repositories>)

    @Query("SELECT * from repositories WHERE page = :page")
    fun getAllRepositories(page: Int): List<Repositories>

}