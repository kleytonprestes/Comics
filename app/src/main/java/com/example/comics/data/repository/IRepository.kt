package com.example.comics.data.repository

import com.example.comics.ui.ItemVO

interface IRepository {
    suspend fun getComics(): List<ItemVO>
}