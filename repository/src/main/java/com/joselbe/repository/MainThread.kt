package com.joselbe.repository

import android.os.Handler
import android.os.Looper

//recibe un cacho codigo y lo lanzo a una cola de ejecucion
fun DispatchOnMainThread(CodeRun: Runnable){
    val uiHandler = Handler(Looper.getMainLooper())
    uiHandler.post(CodeRun)
}