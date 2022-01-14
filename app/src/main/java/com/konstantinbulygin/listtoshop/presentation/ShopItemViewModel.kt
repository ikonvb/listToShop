package com.konstantinbulygin.listtoshop.presentation

import androidx.lifecycle.ViewModel
import com.konstantinbulygin.listtoshop.data.ShopListRepositoryImpl
import com.konstantinbulygin.listtoshop.domain.AddShopItemUseCase
import com.konstantinbulygin.listtoshop.domain.EditShopItemUseCase
import com.konstantinbulygin.listtoshop.domain.GetShopItemUseCase
import com.konstantinbulygin.listtoshop.domain.ShopItem
import java.text.ParseException

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isFieldsValid = validateInput(name, count)
        if (isFieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isFieldsValid = validateInput(name, count)
        if (isFieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: ParseException) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var res = true
        if (name.isBlank()) {
            //TODO : show error input name
            res = false
        }
        if (count <= 0) {
            //TODO: show error input count
            res = false
        }
        return res
    }

}