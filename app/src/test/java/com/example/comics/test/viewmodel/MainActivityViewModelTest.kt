package com.example.comics.test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comics.data.repository.IRepository
import com.example.comics.ui.ItemVO
import com.example.comics.ui.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var repository: IRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        repository = mock()
        viewModel = MainActivityViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test get list success`() {
        val expectedList = listOf(ItemVO(image = "Image", title = "title", subtitle = "subtitle" ),
            ItemVO(image = "Image 2", title = "Title 2", subtitle = "subtitle 2"))
        runTest {
            `when`(repository.getComics()).thenReturn(expectedList)
            viewModel.getList()
        }

        assert(viewModel.itemList.value == expectedList)
        assert(viewModel.hasError.value == false)
    }


    @Test
    fun `test get list failure`() {
        runTest {
            `when`(repository.getComics()).thenThrow(RuntimeException())

            viewModel.getList()
        }

        assert(viewModel.itemList.value == null)
        assert(viewModel.hasError.value == true)
    }
}


