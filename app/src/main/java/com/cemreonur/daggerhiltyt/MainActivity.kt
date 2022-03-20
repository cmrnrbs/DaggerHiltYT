package com.cemreonur.daggerhiltyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    val viewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postAdapter = PostAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = postAdapter

        viewModel.getLiveDataObserver().observe(this, object: Observer<List<Post>>{
            override fun onChanged(t: List<Post>?) {
                if (t != null)
                {
                    postAdapter.setList(t)
                    postAdapter.notifyDataSetChanged()
                }
            }
        })

        viewModel.loadData()
    }
}