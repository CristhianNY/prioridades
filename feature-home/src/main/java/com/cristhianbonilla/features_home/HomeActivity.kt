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
                intent.putExtra(TAG_MAGAZINE, state.pdfMagazineUrl)
                startActivity(intent)
            }

            is PreviewMagazineState.SessionExpired -> {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.register_required))
                    .setMessage(R.string.should_be_log_in) // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(R.string.enter_login,
                        DialogInterface.OnClickListener { dialog, which ->
                            val intent = Intent()
                            intent.setClassName(
                                this,
                                ACTIVITY_AUTHENTICATION
                            )
                            startActivity(intent)
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(getString(R.string.cancel), null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
            is PreviewMagazineState.SubscriptionNotActivated -> {
                AlertDialog.Builder(this)
                    .setTitle(R.string.subcription)
                    .setMessage(R.string.subscription_not_active) // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(R.string.renew,
                        DialogInterface.OnClickListener { dialog, which ->
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(WEB_SUBSCRIPTION)
                            startActivity(i)
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(R.string.cancel, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }

            is ProfileState.LoginRequired -> {
                val intent = Intent()
                intent.setClassName(
                    this,
                    ACTIVITY_AUTHENTICATION
                )
                startActivity(intent)
            }

            is ProfileState.RenewSubscription -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(WEB_SUBSCRIPTION)
                startActivity(i)
            }

            is PreviewMagazineState.GoBack -> {
                innerNavigate(
                    MagazineDetailsFragmentDirections.actionPreviewMagazineFragmentToNavigationHome(
                        "",
                        ""
                    )
                )
            }

            is HomeMagazineState.Search -> {
                val mDialogView =
                    LayoutInflater.from(this).inflate(R.layout.view_search_dialog, null)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle(R.string.search_magazine)

                val mAlertDialog = mBuilder.show()
                mDialogView.keyWordSpinner.setElements(state.keyWords)
                mDialogView.yearsSpinner.setElements(state.years)
                mDialogView.dialogLoginBtn.setOnClickListener {
                    var keyWord: String = mDialogView.keyWordSpinner.selectedItem.toString()
                    if (keyWord == "Palabra clave") {
                        keyWord = ""
                    }

                    val year: String = mDialogView.yearsSpinner.selectedItem.toString()
                    mAlertDialog.dismiss()
                    innerNavigate(HomeFragmentDirections.actionNavigationHomeSelf(year, keyWord))
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

    companion object {
        const val ACTIVITY_AUTHENTICATION =
            "com.cristhianbonilla.feature_login.AuthenticationActivity"
        const val WEB_SUBSCRIPTION = "https://tuiadpa.com/producto/prioridades-suscripcion-anual/"
        const val TAG_MAGAZINE = "URL_MAGAZINE"
    }
}

open class HomeState : BaseState