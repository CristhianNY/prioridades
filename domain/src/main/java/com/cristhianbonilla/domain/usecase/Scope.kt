package com.cristhianbonilla.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    val uiDispatcher: CoroutineDispatcher
    val bgDispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + uiDispatcher

    fun destroyScope() {
        cancel()
    }
}