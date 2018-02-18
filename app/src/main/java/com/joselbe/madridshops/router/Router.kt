package com.joselbe.madridshops.router

import android.content.Intent
import com.joselbe.domain.interactor.TypeObjects
import com.joselbe.domain.model.Shop
import com.joselbe.madridshops.activity.DetailShopActivity
import com.joselbe.madridshops.activity.MenuActivity
import com.joselbe.madridshops.activity.ShopsActivity


class Router{

    fun navigateFromMenuToShops(menu : MenuActivity){
        val intent = Intent(menu, ShopsActivity::class.java)
        intent.putExtra(ShopsActivity.PUT_EXTRA_DATA_TYPE, TypeObjects.SHOPS )
        menu.startActivity(intent)
    }

    fun navigateFromMenuToEvents(menu : MenuActivity){
        val intent = Intent(menu, ShopsActivity::class.java)
        intent.putExtra(ShopsActivity.PUT_EXTRA_DATA_TYPE, TypeObjects.EVENTS )
        menu.startActivity(intent)
    }

    fun navigateToDetail(shops: ShopsActivity, shop : Shop){
        val intent = Intent(shops, DetailShopActivity::class.java)
        intent.putExtra(DetailShopActivity.PUT_EXTRA_DATA, shop)  //SEND THE SERIALIZABLE
        shops.startActivity(intent)
    }
}