package com.cristhianbonilla.foundations.scope

import com.cristhianbonilla.domain.usecase.Scope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class IOScope : Scope {
    override val uiDispatcher: CoroutineDispatcher
        get() = Main
    override val bgDispatcher: CoroutineDispatcher
        get() = IO
}