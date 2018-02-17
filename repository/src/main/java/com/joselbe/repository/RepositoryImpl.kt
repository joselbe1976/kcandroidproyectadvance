package com.joselbe.repository

import android.content.Context
import com.joselbe.repository.cache.Cache
import com.joselbe.repository.cache.CacheImpl
import com.joselbe.repository.model.ShopEntity
import com.joselbe.repository.model.ShopRepsonseEntity
import com.joselbe.repository.network.GetJsonManager
import com.joselbe.repository.network.GetJsonManagerVolleyImpl
import com.joselbe.repository.network.json.JsonEntityParse
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context) : Repository{
    private val weakContext = WeakReference<Context>(context)
    private val cache : Cache = CacheImpl(weakContext.get()!!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        //read All Shops from Cache
        cache.getAllShops(success = {
            //si hay tiendas, pues devilvemos
            success(it)

        }, error = {
            // if not shops in cache --> Network request
            //Store Results in cache
            populateCache(success, error)
        })
    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute("http://madrid-shops.com/json_new/getShops.php",
                success = object : SuccessCompletion<String>{
                    override fun successCompletion(a: String) {

                        //Dado el JSON lo parseamos al modelo Json
                        val parser = JsonEntityParse()
                        val responseEntity  = parser.parse<ShopRepsonseEntity>(a)

                        //persist in Cache
                        cache.saveAllShops(responseEntity.result, success = {
                            success(responseEntity.result) //All Ok returns List of Shops
                        }, error = {
                            error("Error al cachear")
                        })
                    }

                },
                error = object : ErrorCompletion{
                    override fun errorCompletion(errorMessage: String) {
                        //peticion red mal
                        error("Error Red")
                    }
                }
                )


    }

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShops(success, error)
    }

}