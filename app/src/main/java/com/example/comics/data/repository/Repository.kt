package com.example.comics.data.repository

import com.example.comics.data.remote.Api
import com.example.comics.data.remote.mapToItemModelVO
import com.example.comics.ui.ItemVO

class Repository(
    private val api: Api
) : IRepository{

    override suspend fun getComics() : List<ItemVO> {
        return api.getComics().data.results.mapToItemModelVO()
    }
}