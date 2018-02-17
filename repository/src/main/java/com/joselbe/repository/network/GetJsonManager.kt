package com.joselbe.repository.network

import com.joselbe.repository.ErrorCompletion
import com.joselbe.repository.SuccessCompletion


interface GetJsonManager{
    fun execute(url : String, success : SuccessCompletion<String>, error: ErrorCompletion)
}