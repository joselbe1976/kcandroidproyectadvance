package com.joselbe.repository.cache

import com.joselbe.repository.model.ShopEntity

internal interface  Cache{
    fun saveAllShops(type : Int ,shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(type : Int , success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(type : Int ,success: () -> Unit, error: (errorMessage: String) -> Unit)
}