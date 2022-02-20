package com.bulyginkonstantin.listtoshop.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulyginkonstantin.listtoshop.data.ShopListRepositoryImpl
import com.bulyginkonstantin.listtoshop.domain.DeleteShopItemUseCase
import com.bulyginkonstantin.listtoshop.domain.EditShopItemUseCase
import com.bulyginkonstantin.listtoshop.domain.GetShopListUseCase
import com.bulyginkonstantin.listtoshop.domain.ShopItem

class MainViewModel : ViewModel() {

    private val shopListRepository = ShopListRepositoryImpl

    //temp functionality
    val shopList = MutableLiveData<List<ShopItem>>()

    private val getShopListUseCase = GetShopListUseCase(shopListRepository);
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepository);
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepository);

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(isEnable = !shopItem.isEnable)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }

}