package com.bulyginkonstantin.listtoshop.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bulyginkonstantin.listtoshop.data.ShopListRepositoryImpl
import com.bulyginkonstantin.listtoshop.domain.DeleteShopItemUseCase
import com.bulyginkonstantin.listtoshop.domain.EditShopItemUseCase
import com.bulyginkonstantin.listtoshop.domain.GetShopListUseCase
import com.bulyginkonstantin.listtoshop.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}
