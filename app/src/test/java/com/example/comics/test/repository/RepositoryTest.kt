package com.example.comics.test.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comics.data.model.DataModel
import com.example.comics.data.model.ItemModel
import com.example.comics.data.model.ResultModel
import com.example.comics.data.model.ThumbnailModel
import com.example.comics.data.remote.Api
import com.example.comics.data.repository.Repository
import com.example.comics.ui.ItemVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = StandardTestDispatcher()

    private lateinit var api: Api
    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        api = mock()
        repository = Repository(api)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get comics success`() {
        var result = listOf<ItemVO>()
        val expectedResponse =
            ItemModel(
                data = DataModel(
                    listOf(
                        ResultModel(
                            title = "Image",
                            description = "Title",
                            thumbnail = ThumbnailModel(
                                path = "path",
                                extension = "extension"
                            )
                        )
                    )
                )
            )

        runTest {
            `when`(api.getComics()).thenReturn(expectedResponse)

            result = repository.getComics()
        }

        val expectedList = listOf(ItemVO("path.extension", "Image", "Title"))
        assert(expectedList == result)
    }

    @Test(expected = Exception::class)
    fun `test getComics failure`() = runTest {
        `when`(api.getComics()).thenThrow(Exception("Error occurred"))

        repository.getComics()
    }
}