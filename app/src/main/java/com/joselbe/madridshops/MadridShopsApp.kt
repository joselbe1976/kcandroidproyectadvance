package com.joselbe.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.joselbe.domain.interactor.ErrorCompletion
import com.joselbe.domain.interactor.SuccessCompletion
import com.joselbe.domain.interactor.deleteallshops.deleteAllShopsImpl
import com.joselbe.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.joselbe.domain.interactor.getallshops.GetAllShopsInterfactorFakeImplementation
import com.joselbe.domain.model.Shops

import junit.framework.Assert

/**
 * Created by joseluisbustosesteban on 22/1/18.
 */

//hereda de multiDexApplications
class MadridShopsApp : MultiDexApplication() {

    // Aplicacion se crea, antes de iniciar la inicializacion
    override fun onCreate() {
        super.onCreate()

        // código de inicialización de todo el App.
        Log.d("Shops", "OnCreate")



        //pedimos todas las tiendas

        val allShopsIntercatr = GetAllShopsInteractorImpl(this)

        allShopsIntercatr.execute( object : SuccessCompletion<Shops>{
            override fun successCompletion(a: Shops) {
                a.shops.forEach {
                    Log.d("Shops", it.name)
                }
            }

        }, object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Log.d("Shops", errorMessage)
            }
        })




    }


}