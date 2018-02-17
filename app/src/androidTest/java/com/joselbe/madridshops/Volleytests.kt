package com.joselbe.madridshops

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.joselbe.repository.ErrorCompletion
import com.joselbe.repository.SuccessCompletion

import com.joselbe.repository.network.GetJsonManager
import com.joselbe.repository.network.GetJsonManagerVolleyImpl
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Volleytests {

    val appcontext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun valid_url_we_get_not_null_as_string() {

        Log.d("Shop","Empieza a ejecutar")

       val url  = "http://madrid-shops.com/json_new/getShops.php"
       val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(appcontext)

        jsonManager.execute(url, object: SuccessCompletion<String>{
            override fun successCompletion(a: String) {
                Log.d("Shop","success")
                assertTrue(a.isNotEmpty())
            }

        }, object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Log.d("Shop","error")
                 assertTrue(false)
            }
        })
    }
}