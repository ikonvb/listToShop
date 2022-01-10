package com.konstantinbulygin.listtoshop.domain

class DelShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem);
    }
}