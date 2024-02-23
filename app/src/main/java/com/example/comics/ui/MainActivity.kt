package com.example.comics.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comics.extensions.hide
import com.example.comics.extensions.visible
import com.example.comics.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        refresh()
        swipeList()
        viewList()
        error()
    }

    private fun swipeList() = with(binding?.swipeRefresh) {
        this?.setOnRefreshListener {
            refresh()
        }
    }

    private fun refresh() {
        with(binding) {
            this?.swipeRefresh?.isRefreshing = true
            viewModel.getList()

        }
    }

    private fun viewList() {

        viewModel.itemList.observe(this) {
            with(binding) {
                this?.errorTV?.hide()
                this?.listItem?.visible()
                this?.listItem?.adapter = Adapter(it)
                this?.listItem?.layoutManager = LinearLayoutManager(this@MainActivity)
                this?.swipeRefresh?.isRefreshing = false
            }
        }
    }

    private fun error() {
        viewModel.hasError.observe(this) {
            if (it) {
                with(binding) {
                    this?.listItem?.hide()
                    this?.errorTV?.visible()
                    this?.swipeRefresh?.isRefreshing = false
                }
            }
        }
    }

}