package com.konstantinbulygin.listtoshop.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.konstantinbulygin.listtoshop.R
import com.konstantinbulygin.listtoshop.databinding.ActivityMainBinding
import com.konstantinbulygin.listtoshop.presentation.ShopItemActivity.Companion.newIntentAdd
import com.konstantinbulygin.listtoshop.presentation.ShopItemActivity.Companion.newIntentEdit
import com.konstantinbulygin.listtoshop.presentation.ShopItemFragment.OnEditingFinishedListener


class MainActivity : AppCompatActivity(), OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopItemListAdapter: ShopItemListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopItemListAdapter.submitList(it)
        }

        binding.buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = newIntentAdd(this)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceAddItem()
                launchFragment(fragment)
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        with(binding.rvShopList) {
            shopItemListAdapter = ShopItemListAdapter()
            adapter = shopItemListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopItemListAdapter.VIEW_TYPE_ENABLED,
                ShopItemListAdapter.VIEW_HOLDER_MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopItemListAdapter.VIEW_TYPE_DISABLED,
                ShopItemListAdapter.VIEW_HOLDER_MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopItemListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        shopItemListAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = newIntentEdit(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        shopItemListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}