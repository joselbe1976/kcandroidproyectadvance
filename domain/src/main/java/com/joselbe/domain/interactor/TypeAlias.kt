package com.joselbe.domain.interactor

import com.joselbe.domain.model.Shops

typealias  SuccessClosure = (shops: Shops) -> Unit
typealias  ErrorClousure = (msg: String) -> Unit
typealias  CodeClosure = () -> Unit