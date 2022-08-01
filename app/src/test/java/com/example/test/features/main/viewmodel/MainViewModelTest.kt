package com.example.test.features.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.test.core.data.BaseResponse
import com.example.test.core.network.ResultWrapper
import com.example.test.features.main.data.repository.MainRepository
import com.example.test.features.main.data.response.ApiResponse
import com.example.test.features.main.viewstate.MainViewState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @RelaxedMockK
    private lateinit var mainRepository: MainRepository

    @RelaxedMockK
    private lateinit var observer: Observer<MainViewState>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(mainRepository)
        mainViewModel.viewState.observeForever(observer)
    }

    @Test
    fun `call back get list repositories Success`() {
        val success = mockk<BaseResponse<List<ApiResponse>>>()
        every { success.items } returns mockk(relaxed = true)

        coEvery {
            mainRepository.getRepositories(
                any(),
                any(),
                any()
            )
        } returns ResultWrapper.Success(
            success
        )

        val data: Observer<List<ApiResponse>> = mockk(relaxed = true)
        mainViewModel.listRepositories.observeForever(data)

        mainViewModel.getRepositories()

        verifySequence {
            observer.onChanged(MainViewState.Loading(true))
            data.onChanged(mainViewModel.listRepositories.value)
            observer.onChanged(MainViewState.Loading(false))
        }
    }

    @Test
    fun `call back get list repositories Error`() {
        val error = "error_api"
        val code = 403

        coEvery { mainRepository.getRepositories(any(), any(), any()) } returns ResultWrapper.Error(
            error, code
        )

        mainViewModel.getRepositories()

        verifySequence {
            observer.onChanged(MainViewState.Loading(true))
            observer.onChanged(MainViewState.Error(error))
            observer.onChanged(MainViewState.Loading(false))
        }
    }


}