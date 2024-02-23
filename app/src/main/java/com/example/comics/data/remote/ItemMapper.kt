package com.example.comics.data.remote

import com.example.comics.data.model.ResultModel
import com.example.comics.ui.ItemVO


fun List<ResultModel>.mapToItemModelVO() : List<ItemVO> = this.map {

    ItemVO(
        image = "${it.thumbnail.path}.${it.thumbnail.extension}",
        title = it.title,
        subtitle = it.description ?: "Sem descricao"
    )
}