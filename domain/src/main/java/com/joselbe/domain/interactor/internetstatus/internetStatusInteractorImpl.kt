package com.joselbe.domain.interactor.internetstatus

import com.joselbe.domain.interactor.CodeClosure
import com.joselbe.domain.interactor.ErrorClousure

class internetStatusInteractorImpl : internetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClousure) {
        success()
    }
}