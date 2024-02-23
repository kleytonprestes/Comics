package com.example.comics.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.data.remote.SafeResponse
import com.example.comics.data.remote.safeRequest
import com.example.comics.data.repository.IRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: IRepository
) : ViewModel() {

    private val _itemList = MutableLiveData<List<ItemVO>>()

    val itemList: LiveData<List<ItemVO>>
        get() = _itemList

    private val _hasError = MutableLiveData<Boolean>()

    val hasError: LiveData<Boolean>
        get() = _hasError

    fun getList() {
        viewModelScope.launch {
            when(
                val response = safeRequest {
                    repository.getComics()
                }
            ) {
                is SafeResponse.Success -> {
                    response.value.let {
                        _itemList.value = it
                        _hasError.value = false
                    }
                }
                else -> {
                    _hasError.value = true
                }
            }
        }
    }
}