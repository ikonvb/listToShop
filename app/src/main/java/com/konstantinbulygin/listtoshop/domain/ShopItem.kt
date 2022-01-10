package com.konstantinbulygin.listtoshop.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val isEnable: Boolean
)