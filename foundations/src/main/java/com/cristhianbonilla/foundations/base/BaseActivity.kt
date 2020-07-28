package com.cristhianbonilla.foundations.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.foundations.extensions.injectInteraction
import com.cristhianbonilla.foundations.extensions.injectNavigator
import com.cristhianbonilla.foundations.interaction.Interaction
import com.cristhianbonilla.foundations.navigation.Navigator
import org.koin.android.ext.android.inject
import java.lang.reflect.Type

abstract class BaseActivity<S : BaseState>(
    private val layoutId: Int,
    private val graphId: Int? = null,
    private val navControllerId: Int? = null
) : AppCompatActivity(), BaseStateListener<S> {
    private val mode: ModeTypeModel by inject()
    protected val interaction: Interaction by injectInteraction()
    private var innerNavigator: NavController? = null
    protected val navigator: Navigator by injectNavigator()
    private var resultType: Type? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mode == ModeTypeModel.RELEASE) {
            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }
        setContentView(layoutId)

        if(navControllerId != null && graphId != null) {
            intent?.extras?.let { findNavController(navControllerId).setGraph(graphId, it) }
        }
    }

    @CallSuper
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? =
        super.onCreateView(parent, name, context, attrs)?.apply {
            innerNavigator = Navigation.findNavController(this)
        }



    override fun onStateChanged(state: S) {
        /** to override **/
    }

    final override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val resultMap = mutableMapOf<String, Any?>()
        data?.extras?.keySet()?.forEach{ resultMap[it] = data.extras?.get(it) }
        onNavigationResult(requestCode, resultCode, resultMap)
    }

    protected open fun onNavigationResult(requestCode: Int, resultCode: Int, result: Map<String, Any?>){
        /** to override **/
    }

    protected fun finishWithResult(resultCode: Int, bundle: Bundle){
        val resultIntent = Intent().apply { putExtras(bundle) }
        setResult(resultCode, resultIntent)
        finish()
    }

    protected fun innerNavigate(directions: NavDirections) {
        innerNavigator?.navigate(directions)
    }

    protected fun innerNavigate(resId: Int) {
        innerNavigator?.navigate(resId)
    }
}