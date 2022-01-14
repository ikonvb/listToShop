package com.konstantinbulygin.listtoshop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.konstantinbulygin.listtoshop.R
import com.konstantinbulygin.listtoshop.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.shopItemList = it

        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopItemListAdapter()
        with(rvShopList) {
            adapter = adapter
            recycledViewPool.setMaxRecycledViews(ShopItemListAdapter.VIEW_TYPE_ENABLED, ShopItemListAdapter.VIEW_HOLDER_MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopItemListAdapter.VIEW_TYPE_DISABLED, ShopItemListAdapter.VIEW_HOLDER_MAX_POOL_SIZE)
        }
    }
}