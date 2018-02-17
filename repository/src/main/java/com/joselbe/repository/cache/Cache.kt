package com.joselbe.repository.cache

import com.joselbe.repository.model.ShopEntity

internal interface  Cache{
    fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}