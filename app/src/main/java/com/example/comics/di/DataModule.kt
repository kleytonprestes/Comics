package com.example.comics.di

import com.example.comics.data.repository.IRepository
import com.example.comics.data.repository.Repository

import org.koin.dsl.module

val dataModule = module {

    single<IRepository> {
        Repository(
            api = get()
        )
    }
}