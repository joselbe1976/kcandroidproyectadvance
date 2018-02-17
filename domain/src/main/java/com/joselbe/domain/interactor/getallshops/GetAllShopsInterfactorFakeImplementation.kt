package com.joselbe.domain.interactor.getallshops

import com.joselbe.domain.interactor.ErrorClousure
import com.joselbe.domain.interactor.ErrorCompletion
import com.joselbe.domain.interactor.SuccessClosure
import com.joselbe.domain.interactor.SuccessCompletion
import com.joselbe.domain.model.Shop
import com.joselbe.domain.model.Shops

class GetAllShopsInterfactorFakeImplementation : GetAllShopsInteractor {
    override fun execute(type: Int, success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOK = true

        //conect to the repository

        if (allOK){
            val shops = createFakeShops()
            success.successCompletion(shops)
        }
        else{
            error.errorCompletion("Error al buscar los datos en red")
        }
    }

    //con Lambdas
    fun execute(success : SuccessClosure, error: ErrorClousure){
        var allOK = true

        /*  clausura de codigo comun, para no duplicar codigo
        val commonCode  = {
            val n = 10
            print("Hellou " +n)
        }
        */

        if (allOK){

            //commonCode()
            val shops = createFakeShops()
            success(shops)
        }
        else{
            error("Error al buscar los datos en red")
        }
    //    commonCode()
    }


    fun createFakeShops() : Shops{
        val list = ArrayList<Shop>()
        for(i in 0..100){
         //   val shop = Shop(i, "Shop"+i, "Address " + i)
        //    list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }

}