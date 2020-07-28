package com.cristhianbonilla.foundations.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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


    override fun onStateChanged(state: S) {
        TODO("Not yet implemented")
    }
}