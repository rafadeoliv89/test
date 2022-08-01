package com.example.test.features.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.test.core.base.BaseViewModel
import com.example.test.core.network.ResultWrapper
import com.example.test.features.main.data.repository.MainRepository
import com.example.test.features.main.data.response.ApiResponse
import com.example.test.features.main.utils.MainConstants
import com.example.test.features.main.viewstate.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) : BaseViewModel() {

    val viewState: MutableLiveData<MainViewState> = MutableLiveData()
    val listRepositories: MutableLiveData<List<ApiResponse>> = MutableLiveData()

    private var page = 1

    fun getRepositories() {
        scope.launch {
            viewState.value = MainViewState.Loading(true)
            when (val response = repository.getRepositories(
                page,
                MainConstants.SORT,
                MainConstants.QUERY
            )) {
                is ResultWrapper.Success -> {
                    page++
                    listRepositories.value = response.value.items

                }
                is ResultWrapper.Error -> {
                    viewState.value = MainViewState.Error(response.message)
                }
            }
            viewState.value = MainViewState.Loading(false)
        }
    }

}