package com.joselbe.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.joselbe.repository.db.DBHelper
import com.joselbe.repository.db.buildDBHelper
import com.joselbe.repository.db.dao.ShopDAO
import com.joselbe.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ShopDAOTest {


    @Test
    @Throws(Exception::class)
    fun given_valid_shops_it_gets_inserted_correctly() {
        //contexto
        val appContext = InstrumentationRegistry.getTargetContext()
        //creo conexion base de datos
        val dbhelper = buildDBHelper(appContext,"mydb.sqlite",1)


        //creo un modelo
        val shop = ShopEntity(1,1,"my shop", "","", "1.0","2.0","","","","", "")
        val shopEntityDao = ShopDAO(TypeObjects.SHOPS ,dbhelper)

        //inserto
        val id = shopEntityDao.insert(shop)
        assertTrue( id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_clean_caches() {
        //contexto
        val appContext = InstrumentationRegistry.getTargetContext()
        //creo conexion base de datos
        val dbhelper = buildDBHelper(appContext,"mydb.sqlite",1)

          val shopEntityDao = ShopDAO(TypeObjects.SHOPS ,dbhelper)

        //inserto
        val res = shopEntityDao.deleteAll()
        assertEquals( res, true)
    }

}
