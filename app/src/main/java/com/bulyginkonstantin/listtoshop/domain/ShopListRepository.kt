package com.bulyginkonstantin.listtoshop.domain

import androidx.lifecycle.LiveData
import com.bulyginkonstantin.listtoshop.domain.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}
