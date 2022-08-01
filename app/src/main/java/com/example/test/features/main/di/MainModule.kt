package com.example.test.features.main.di

import android.content.Context
import com.example.test.core.data.AppDatabase
import com.example.test.core.data.RepositoriesDao
import com.example.test.core.network.RetrofitProvider
import com.example.test.features.main.data.MainService
import com.example.test.features.main.data.repository.MainRepository
import com.example.test.features.main.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideMainService(): MainService {
        return RetrofitProvider.getApiService()
    }

    @Provides
    fun provideMainRepository(service: MainService, repositoriesDao: RepositoriesDao): MainRepository {
        return MainRepositoryImpl(service, repositoriesDao)
    }

    @Provides
    fun provideRepositoriesDao(@ApplicationContext context: Context): RepositoriesDao {
        return AppDatabase.getInstance(context).repositoriesDao()
    }
}