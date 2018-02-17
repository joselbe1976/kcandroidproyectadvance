package com.joselbe.domain.interactor.deleteallshops

import android.content.Context
import com.joselbe.domain.interactor.CodeClosure
import com.joselbe.domain.interactor.ErrorClousure
import com.joselbe.repository.Repository
import com.joselbe.repository.RepositoryImpl
import java.lang.ref.WeakReference

class deleteAllShopsImpl (val type: Int, context: Context) : deleteAllShops {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClousure) {
        val repository = RepositoryImpl(weakContext.get()!!)
        repository.deleteAllShops(type,success, error)
    }
}