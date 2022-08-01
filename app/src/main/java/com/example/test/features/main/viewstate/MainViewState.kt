package com.example.test.features.main.viewstate

sealed class MainViewState {
    data class Error(val message: String) : MainViewState()
    data class Loading(val show: Boolean) : MainViewState()
}