package com.exercicio.tarefa.feature.services

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exercicio.tarefa.R
import kotlinx.android.synthetic.main.activity_services.*

class ServicesActivity : AppCompatActivity() {

    companion object {
        fun launchIntent(context: Context) = Intent(context, ServicesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        bindListeners()
    }

    private fun bindListeners() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}