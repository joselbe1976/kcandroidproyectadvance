package com.joselbe.repository

interface SuccessCompletion <T>{
    fun successCompletion(a: T)
}

interface ErrorCompletion{
    fun  errorCompletion(errorMessage: String)
}