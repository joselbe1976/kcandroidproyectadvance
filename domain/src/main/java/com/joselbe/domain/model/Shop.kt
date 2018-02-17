package com.joselbe.domain.model

import java.io.Serializable

/**
 * Shop represent 1 shop   ( it.description, it.img, it.logo, it.openingHours
 */
data class Shop(val id: Int, val name: String, val  address: String, val lat_gps : String, val long_gps: String, val description : String, val img : String, val logo : String, val OpenHours : String) : Serializable{

    //no es constructor, sino codigo que se ejecuta con el constructor
    init {
        Shops(ArrayList<Shop>())
    }
}


/**
 * List of Shop using generics
 * */
class Shops(var shops: MutableList<Shop>) : Agregate<Shop> , Serializable{

    override fun count(): Int =  shops.size
    override fun all(): List<Shop> = shops
    override fun get(pos: Int): Shop  = shops.get(pos)

    override fun add(element: Shop) {
       shops.add(element)
    }

    override fun delete(pos: Int) {
        shops.removeAt(pos)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }


}

