package com.joselbe.repository

import com.joselbe.repository.model.ShopEntity

interface  Repository{
    fun deleteAllShops(type : Int ,success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(type : Int , success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
}