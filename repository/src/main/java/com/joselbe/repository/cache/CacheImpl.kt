package com.joselbe.repository.cache

import com.joselbe.repository.db.DBHelper
import com.joselbe.repository.db.buildDBHelper
import com.joselbe.repository.db.dao.ShopDAO
import android.content.Context
import com.joselbe.repository.DispatchOnMainThread
import com.joselbe.repository.model.ShopEntity
import java.lang.ref.WeakReference


class CacheImpl( context : Context) : Cache {
    val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops =  ShopDAO(cacheDBHelper()).query()

            //lanzamos por el Hilo principal
            DispatchOnMainThread(Runnable{
                if (shops.count() > 0) {
                    success(shops)
                } else{
                    error("No shops")
                }
            })
        }).run()

    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainThread(Runnable{
                    success()
                })
            }
            catch(e: Exception){
                DispatchOnMainThread(Runnable{
                    error("Error Inserting Shops")
                })
            }
        }).run()

    }



    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        Thread(Runnable {
            var successDeleting =  ShopDAO(cacheDBHelper()).deleteAll()
                //lanzamos por el Hilo principal
                DispatchOnMainThread(Runnable{
                    if (successDeleting) {
                        success()
                    } else{
                        error("Error al Eliminar")
                    }
                })
        }).run()

    }

    private fun cacheDBHelper() : DBHelper{
        return buildDBHelper(this.context.get()!!, "MadridShops.sqlite",1)
    }
}