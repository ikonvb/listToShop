package com.konstantinbulygin.listtoshop.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konstantinbulygin.listtoshop.R
import com.konstantinbulygin.listtoshop.domain.ShopItem

class ShopItemListAdapter : RecyclerView.Adapter<ShopItemListAdapter.ShopItemViewHolder>() {

    var shopItemList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1;
        const val VIEW_TYPE_DISABLED = 0;
        const val VIEW_HOLDER_MAX_POOL_SIZE = 20;
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if (item.isEnable) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }
}