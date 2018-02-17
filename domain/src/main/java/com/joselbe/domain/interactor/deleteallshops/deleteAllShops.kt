package com.joselbe.domain.interactor.deleteallshops

import com.joselbe.domain.interactor.CodeClosure
import com.joselbe.domain.interactor.ErrorClousure

interface deleteAllShops{
    fun execute(success: CodeClosure, error: ErrorClousure)
}