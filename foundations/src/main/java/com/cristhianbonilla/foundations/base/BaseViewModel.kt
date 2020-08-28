package com.cristhianbonilla.foundations.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cristhianbonilla.domain.usecase.Scope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.java.KoinJavaComponent
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S : BaseState, UI : BaseData<S>, T : BaseTracker>(
    private val scope: Scope,
    protected val data: UI,
    protected val tracker: T
) : AndroidViewModel(KoinJavaComponent.getKoin().get()),
    CoroutineScope {
    private val job by lazy { SupervisorJob() }
    override val coroutineContext: CoroutineContext
        get() = scope.uiDispatcher + job

    fun bindData(
        binding: ViewDataBinding,
        dataBR: Int
    ) {
        binding.setVariable(dataBR, data)
    }

    fun observeDataState(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<S>
    ) {
        data.observeState(lifecycleOwner, observer)
    }

    fun restartState() {
        data.clearState()
    }


    internal fun trackOnViewDisplayed() {
        tracker.viewDisplayed()
    }
}