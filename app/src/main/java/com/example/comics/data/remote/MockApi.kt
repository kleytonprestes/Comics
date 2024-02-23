package com.example.comics.data.remote

import com.example.comics.data.model.DataModel
import com.example.comics.data.model.ItemModel
import com.example.comics.data.model.ResultModel
import com.example.comics.data.model.ThumbnailModel

class MockApi: Api {
    override suspend fun getComics(ts: String, apikey: String, hash: String): ItemModel {
        return ItemModel(
            data = DataModel(
                results = listOf(
                    ResultModel(
                        title = "Title",
                        description = "Descrição",
                        thumbnail = ThumbnailModel(
                            path = "path",
                            extension = "extension"
                        )
                    )
                )
            )
        )
    }
}