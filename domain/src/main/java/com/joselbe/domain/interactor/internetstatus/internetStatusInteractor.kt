package com.joselbe.domain.interactor.internetstatus

import com.joselbe.domain.interactor.CodeClosure
import com.joselbe.domain.interactor.ErrorClousure

interface internetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClousure)
}