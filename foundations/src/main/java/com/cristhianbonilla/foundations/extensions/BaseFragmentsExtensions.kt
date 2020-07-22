package com.cristhianbonilla.foundations.extensions

import androidx.databinding.ViewDataBinding
import com.cristhianbonilla.foundations.base.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

private const val INDEX_BASE_VIEW_MODEL: Int = 3

internal fun <S : BaseState,
        UI : BaseData<S>,
        T : BaseTracker,
        VM : BaseViewModel<S, UI, T>,
        DB : ViewDataBinding> BaseFragment<S, UI, T, VM, DB>.injectViewModel() =
    lifecycleScope.getViewModel(this, getViewModelClass())


@Suppress("UNCHECKED_CAST")
private fun <S : BaseState,
        UI : BaseData<S>,
        T : BaseTracker,
        VM : BaseViewModel<S, UI, T>,
        DB : ViewDataBinding> BaseFragment<S, UI, T, VM, DB>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[INDEX_BASE_VIEW_MODEL] as Class<VM>).kotlin