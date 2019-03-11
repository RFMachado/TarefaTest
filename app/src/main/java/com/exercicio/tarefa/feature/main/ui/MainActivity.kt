package com.exercicio.tarefa.feature.main.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.ui.DetailActivity
import com.exercicio.tarefa.util.extensions.hide
import com.exercicio.tarefa.util.extensions.show
import com.exercicio.tarefa.util.shared.ItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val items = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupViewModel()

        viewModel.fetchItems()
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@MainActivity)
        addItemDecoration(ItemDecoration(ContextCompat.getDrawable(context, R.drawable.item_divider)!!))
        adapter = MainAdapter(items) { itemId ->
            val intent = DetailActivity.launchIntent(this@MainActivity, itemId)
            startActivity(intent)
        }
    }

    private fun setupViewModel() {
        viewModel.loadData.observe(this, Observer { isLoading ->
            when(isLoading) {
                true -> showLoading()
                false -> hideLoading()
            }
        })

        viewModel.uiData.observe(this, Observer { uiData ->
            when{
                uiData?.itemsData != null -> showData(uiData.itemsData)
                uiData?.error is Throwable -> showError(uiData.error)
            }
        })
    }

    private fun showLoading() {
        progressBar.show()
    }

    private fun hideLoading() {
        progressBar.hide()
    }

    private fun showData(itemsList: List<String>) {
        items.clear()
        items.addAll(itemsList)

        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
    }
}
