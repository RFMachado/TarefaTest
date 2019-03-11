package com.exercicio.tarefa.feature.detail.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import com.bumptech.glide.Glide
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.domain.entity.ActionsData
import com.exercicio.tarefa.feature.detail.domain.entity.Detail
import com.exercicio.tarefa.feature.detail.domain.entity.MapData
import com.exercicio.tarefa.feature.detail.domain.entity.ProfileData
import com.exercicio.tarefa.feature.services.ServicesActivity
import com.exercicio.tarefa.util.extensions.alert
import com.exercicio.tarefa.util.extensions.hide
import com.exercicio.tarefa.util.extensions.show
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import android.support.v7.widget.RecyclerView
import android.view.View
import com.exercicio.tarefa.util.Util


class DetailActivity: AppCompatActivity(), DetailAdapter.ActionsListener {

    private val itemId by lazy { intent.getStringExtra(EXTRA_ITEM_ID) }

    private val viewModel: DetailViewModel by viewModel()
    private val items = arrayListOf<Any>()

    lateinit var smoothScroller: RecyclerView.SmoothScroller

    companion object {
        const val EXTRA_ITEM_ID = "itemId"

        fun launchIntent(context: Context, itemId: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ITEM_ID, itemId)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        bindListeners()
        setupViewModel()
        setupRecyclerView()

        viewModel.fetchItems(itemId)
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@DetailActivity)
        adapter = DetailAdapter(items, this@DetailActivity)


        smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }

            override fun calculateDyToMakeVisible(view: View?, snapPreference: Int): Int {
                val layoutManager = layoutManager
                if (!layoutManager?.canScrollVertically()!!) {
                    return 0
                }
                val params = view?.layoutParams as RecyclerView.LayoutParams
                val top = layoutManager.getDecoratedTop(view)
                val bottom = layoutManager.getDecoratedBottom(view) + params.bottomMargin
                val viewHeight = bottom - top
                val start = layoutManager.paddingTop + resources.getDimension(R.dimen.topbar_size).toInt()
                val end = start + viewHeight

                return calculateDtToFit(top, bottom, start, end, snapPreference)
            }
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
                uiData?.detailData != null -> showData(uiData.detailData)
                uiData?.error is Throwable -> showError(uiData.error)
            }
        })
    }

    private fun bindListeners() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showLoading() {
        progressBar.show()
    }

    private fun hideLoading() {
        progressBar.hide()
    }

    private fun showData(detail: Detail) {
        txtTopBar.text = getString(R.string.top_bar_title, detail.city, detail.neighborhood)

        Glide.with(this)
            .load(detail.urlLogo)
            .into(imgLogo)

        Glide.with(this)
            .load(detail.urlPhoto)
            .into(imgPhoto)

        items.clear()

        items.add(ProfileData(detail))
        items.add(ActionsData(detail))
        items.add(detail.text)
        items.add(MapData(detail))
        items.addAll(detail.comments)
        items.addAll(detail.comments)

        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun onClickServices() {
        val intent = ServicesActivity.launchIntent(this)
        startActivity(intent)
    }

    override fun onClickCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }


    override fun onClickAddress(address: String) {
        alert {
            setTitle(address)
            setCancelable(false)
            setNegativeButton(R.string.ok) { _, _-> }
        }
    }

    override fun onClickComments() {
        appBar.setExpanded(false, true)
        smoothScroller.targetPosition = 5
        recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
    }

}