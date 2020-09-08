package com.cristhianbonilla.features_home.ui.details

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.domain.usecase.magazinepdf.GetMagazinePdfUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute


class PreviewMagazineViewModel(
    scope: Scope,
    data: PreviewMagazineData,
    tracker: PreviewMagazineTracker,
    private val getKeyWordsUseCase: GetKeyWordsUseCase,
    private val getMagazineListUseCase: GetMagazineListUseCase,
    private val getMagazinePdfUseCase: GetMagazinePdfUseCase
) :
    BaseViewModel<PreviewMagazineState, PreviewMagazineData, PreviewMagazineTracker>(
        scope,
        data,
        tracker
    ) {

    fun setContent(item: MagazineModelItem) {
        data.setFragmentContent(item)
    }

    val goBack = {
        data.onGoBack()
    }

    fun getMagazinePdf() {
        data.loading()
        execute {

            getMagazinePdfUseCase(GetMagazinePdfUseCase.Params(data.magazineId.value)).fold(
                ::handleGetMagazinePdfFailure,
                ::handleMagazinPdfSuccess
            )
        }
    }

    private fun handleMagazinPdfSuccess(magazinePdf: MagazinePdfModel) {
        data.success()
        magazinePdf.magazine?.pdf?.let { data.setMagazinePdf(it) }
        magazinePdf.magazine?.pdf?.let { data.onReadMagazinePdf(it) }

    }

    private fun handleGetMagazinePdfFailure(failure: Failure) {
        when (failure) {
            is Failure.SessionExpired -> {
                data.sessionExpiredState()
            }
            is Failure.SubscriptionNotActivated -> {
                data.subscriptionNotActivated()
            }
        }
    }


}