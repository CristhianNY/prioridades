package com.cristhianbonilla.foundations.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cristhianbonilla.foundations.extensions.injectViewModel

class BaseFragment<S : BaseState,
        D : BaseData<S>,
        T : BaseTracker,
        out VM : BaseViewModel<S, D, T>,
        DB : ViewDataBinding>(
    private val layoutId: Int,
    private val viewModelBR: Int,
    private val dataBR: Int
) : Fragment() {

    protected val viewModel: VM
        get() = injectViewModel()


    protected open val onBackPressedAction: (OnBackPressedCallback.() -> Unit)? = null

    private var stateListener: BaseStateListener<S>? = null

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    @CallSuper
    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? BaseStateListener<S>)?.let { stateListener = it }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate(inflater, layoutId, container, false) as DB
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(viewModelBR, viewModel)
        }
        viewModel.apply {
            bindData(binding, dataBR)
            observeDataState(
                viewLifecycleOwner,
                Observer { newState -> newState?.let { stateListener?.onStateChanged(it) } })
        }
        onBackPressedAction?.let {
            onBackPressedCallback =
                requireActivity().onBackPressedDispatcher.addCallback(onBackPressed = it)
        }

        return binding.root
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        viewModel.restartState()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        if (onBackPressedAction != null) {
            onBackPressedCallback.remove()
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        viewModel.trackOnViewDisplayed()
    }

    final override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    final override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
    }

    final override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    final override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
    }
}