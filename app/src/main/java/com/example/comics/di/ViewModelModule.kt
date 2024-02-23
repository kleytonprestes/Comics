package com.example.comics.di

import com.example.comics.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainActivityViewModel(
            repository = get()
        )
    }
}