package com.bulyginkonstantin.listtoshop.domain


class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList();
    }
}