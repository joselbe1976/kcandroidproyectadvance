package com.joselbe.domain.interactor.getallshops

import android.content.Context
import com.joselbe.domain.interactor.ErrorCompletion
import com.joselbe.domain.interactor.SuccessCompletion
import com.joselbe.domain.model.Shop
import com.joselbe.domain.model.Shops
import com.joselbe.repository.Repository
import com.joselbe.repository.RepositoryImpl
import com.joselbe.repository.model.ShopEntity
import java.lang.ref.WeakReference
import java.util.*

class GetAllShopsInteractorImpl (context : Context) : GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            //it = ShopEntity. Hay que mapear shopentoty -> Shops
            val shops: Shops = EntityMapper(it)
            success.successCompletion(shops) //ok.
        }, error = {
            error(it)
        })
    }

    //mapping
    private fun EntityMapper(list: List<ShopEntity>) : Shops{
        val templList = ArrayList<Shop>()

        list.forEach {

            val shop : Shop?

            //languaje Control with descriptiokm and Opening Hours (EnGlish / Spanish)

            if (Locale.getDefault().language.equals("es")){
                 shop = Shop(it.id.toInt(), it.name, it.address, it.latitude.replace(",",""), it.longitude.replace(",",""), it.description_es, it.img, it.logo, it.openingHours_es)

            }
            else{
                 shop = Shop(it.id.toInt(), it.name, it.address, it.latitude.replace(",",""), it.longitude.replace(",",""), it.description_en, it.img, it.logo, it.openingHours_en)

            }



            templList.add(shop!!)
        }

        return  Shops(templList)
    }

}