package com.bulyginkonstantin.listtoshop.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val isEnable: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }

}