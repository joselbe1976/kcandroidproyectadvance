package com.joselbe.madridshops.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.util.*

// Control de Conexion a la red del device
    fun hasConnection( context: Context) : Boolean{

         var cm: ConnectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

         var activeNetwork: NetworkInfo? = cm.activeNetworkInfo
         var isConnected = activeNetwork != null && activeNetwork!!.isConnectedOrConnecting

        return isConnected
    }

    fun getLanguajeDevice(){

    }



//languaje
 fun getLanguageID(): String {
    return Locale.getDefault().language
}
