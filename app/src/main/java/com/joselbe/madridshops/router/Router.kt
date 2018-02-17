package com.joselbe.madridshops.router

import android.content.Intent
import com.joselbe.domain.model.Shop
import com.joselbe.madridshops.activity.DetailShopActivity
import com.joselbe.madridshops.activity.MenuActivity
import com.joselbe.madridshops.activity.ShopsActivity
import com.joselbe.madridshops.activity.PicassoActivity

class Router{
    fun navigateFromMainToPicassoActivity(shops: ShopsActivity){
        shops.startActivity(Intent(shops, PicassoActivity::class.java))
    }


    fun navigateFromMenuToShops(menu : MenuActivity){
        menu.startActivity(Intent(menu, ShopsActivity::class.java))
    }

    fun navigateToDetail(shops: ShopsActivity, shop : Shop){
        val intent = Intent(shops, DetailShopActivity::class.java)
        intent.putExtra(DetailShopActivity.PUT_EXTRA_DATA, shop)  //SEND THE SERIALIZABLE
        shops.startActivity(intent)
    }
}