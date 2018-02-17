package com.joselbe.domain.interactor


interface SuccessCompletion <T>{
    fun successCompletion(a: T)
}

interface ErrorCompletion{
    fun  errorCompletion(errorMessage: String)
}