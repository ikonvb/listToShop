package com.konstantinbulygin.listtoshop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konstantinbulygin.listtoshop.data.ShopListRepositoryImpl
import com.konstantinbulygin.listtoshop.domain.DelShopItemUseCase
import com.konstantinbulygin.listtoshop.domain.EditShopItemUseCase
import com.konstantinbulygin.listtoshop.domain.GetShopListUseCase
import com.konstantinbulygin.listtoshop.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val delShopItemUseCase = DelShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        delShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(isEnable = !shopItem.isEnable)
        editShopItemUseCase.editShopItem(newItem)
    }
}