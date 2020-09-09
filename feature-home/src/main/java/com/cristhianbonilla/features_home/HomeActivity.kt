package com.cristhianbonilla.features_home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_magazine_reader.magazineViewer.ReaderMagazineActivity
import com.cristhianbonilla.features_home.ui.details.MagazineDetailsFragmentDirections
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState
import com.cristhianbonilla.features_home.ui.home.HomeFragmentDirections
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState
import com.cristhianbonilla.features_home.ui.perfil.ProfileState
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.view_search_dialog.*
import kotlinx.android.synthetic.main.view_search_dialog.view.*


class HomeActivity : BaseActivity<HomeState>(
    R.layout.activity_home,
    R.navigation.mobile_navigation,
    R.id.nav_host_fragment
) {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment)

        val topLevelDestination = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications
        )
        appBarConfiguration = AppBarConfiguration(topLevelDestination, parentDrawer)
        setupBottomNavigation()

    }

    override fun onStateChanged(state: HomeState) {
        super.onStateChanged(state)

        when (state) {
            is HomeMagazineState.NavigateToMagazineDetails -> {
                innerNavigate(
                    HomeFragmentDirections.actionNavigationHomeToPreviewMagazineFragment(
                        state.item
                    )
                )
            }

            is PreviewMagazineState.NavigateToMagazineReader -> {
                val intent = Intent(baseContext, ReaderMagazineActivity::class.java)
                intent.putExtra("URL_MAGAZINE", state.pdfMagazineUrl)
                startActivity(intent)
            }

            is PreviewMagazineState.SessionExpired -> {
                AlertDialog.Builder(this)
                    .setTitle("Registro requerido")
                    .setMessage("Debes iniciar sesión o registrarte para leer la revista”") // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("“Ingresar”",
                        DialogInterface.OnClickListener { dialog, which ->
                            val intent = Intent()
                            intent.setClassName(
                                this,
                                "com.cristhianbonilla.feature_login.AuthenticationActivity"
                            )
                            startActivity(intent)
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton("Cancelar", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
            is PreviewMagazineState.SubscriptionNotActivated -> {
                AlertDialog.Builder(this)
                    .setTitle("Subscripción")
                    .setMessage("Subscripción no activa") // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton("Renovar",
                        DialogInterface.OnClickListener { dialog, which ->
                            val url = "https://tuiadpa.com/producto/prioridades-suscripcion-anual/"
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(url)
                            startActivity(i)
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton("Cancelar", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }

            is ProfileState.LoginRequired -> {
                val intent = Intent()
                intent.setClassName(
                    this,
                    "com.cristhianbonilla.feature_login.AuthenticationActivity"
                )
                startActivity(intent)
            }

            is PreviewMagazineState.GoBack -> {
                innerNavigate(MagazineDetailsFragmentDirections.actionPreviewMagazineFragmentToNavigationHome("",""))
            }

            is HomeMagazineState.Search -> {
                val mDialogView =
                    LayoutInflater.from(this).inflate(R.layout.view_search_dialog, null)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Buscar Revistas")

                val mAlertDialog = mBuilder.show()
                mDialogView.keyWordSpinner.setElements(state.keyWords)
                mDialogView.yearsSpinner.setElements(state.years)
                mDialogView.dialogLoginBtn.setOnClickListener {
                    var keyWord: String = mDialogView.keyWordSpinner.selectedItem.toString()
                    if(keyWord=="Palabra clave"){
                        keyWord = ""
                    }

                    val year: String =  mDialogView.yearsSpinner.selectedItem.toString()
                    mAlertDialog.dismiss()
                    innerNavigate(HomeFragmentDirections.actionNavigationHomeSelf(year,keyWord))
                }

                mDialogView.dialogCancelBtn.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun setupBottomNavigation() {
        nav_view.apply {
            setupWithNavController(navController)
            setOnNavigationItemSelectedListener { menuItem ->
                bottomNavigationItemSelected(menuItem.itemId)
                true
            }
        }
    }

    private fun bottomNavigationItemSelected(itemId: Int) {
        navController.navigate(
            when (itemId) {
                R.id.navigation_home -> R.id.navigation_home
                R.id.navigation_dashboard -> R.id.navigation_dashboard
                R.id.navigation_notifications -> R.id.navigation_notifications
                else -> R.id.navigation_home
            }
        )
    }
}

open class HomeState : BaseState