package com.joselbe.domain.interactor.getallshops

import com.joselbe.domain.interactor.ErrorCompletion
import com.joselbe.domain.interactor.SuccessCompletion
import com.joselbe.domain.model.Shops

interface GetAllShopsInteractor {

    // the type are Shops or Events
    fun execute(type : Int,  success: SuccessCompletion<Shops>, error : ErrorCompletion)
}