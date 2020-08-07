package com.cristhianbonilla.features_home.ui.details

import android.util.Log
import android.widget.Toast
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


    fun getMagazinePdf() {
        data.loading()
        execute {
            getMagazinePdfUseCase(GetMagazinePdfUseCase.Params(data.magazineId.value)).fold(
                { handleGetMagazinePdfFailure() },
                ::handleMagazinPdfSuccess
            )
        }
    }

    private fun handleMagazinPdfSuccess(magazinePdf: MagazinePdfModel) {
        data.success()
        Toast.makeText(context, magazinePdf.magazine?.pdf, Toast.LENGTH_LONG).show()
        magazinePdf.magazine?.pdf?.let { data.setMagazinePdf(it) }
    }

    private fun handleGetMagazinePdfFailure() {
        data.error()
        Log.d("Error url del pdf", "Error Al traer magazines")
    }
}