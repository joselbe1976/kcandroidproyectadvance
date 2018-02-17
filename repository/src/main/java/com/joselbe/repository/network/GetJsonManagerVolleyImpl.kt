package com.joselbe.repository.network

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.joselbe.repository.ErrorCompletion
import com.joselbe.repository.SuccessCompletion
import java.lang.ref.WeakReference

class GetJsonManagerVolleyImpl (context : Context): GetJsonManager{

    // Actividad -> (crea) Inrerractor Striong -> Respoitorio (Strong) -> Volle(Strong) -/> Actividad (weak)

    //Weakreference, crea puntero Weak
    val weakContext : WeakReference<Context> = WeakReference(context)
    var requestQueue : RequestQueue? = null

    override fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion) {

        //create request (success, failure)
        val request = StringRequest(url, Response.Listener {
            Log.d("Shop", it)
            success.successCompletion(it)  //llamamos a la clausura que nos pasen
        }, Response.ErrorListener{
            error.errorCompletion(it.localizedMessage) //lalmamos clausura de error
        })


        //add request to queue
        requestQueue().add(request)
    }

    //get request Queue
    fun requestQueue() : RequestQueue{
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(weakContext.get())
         }
        return requestQueue!!
    }

}