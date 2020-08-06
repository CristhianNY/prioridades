package com.cristhianbonilla.features_home.ui.details

import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel

class PreviewMagazineViewModel(scope: Scope,
                               data: PreviewMagazineData,
                               tracker: PreviewMagazineTracker,
                               private val getKeyWordsUseCase: GetKeyWordsUseCase,
                               private val getMagazineListUseCase: GetMagazineListUseCase
):
    BaseViewModel<PreviewMagazineState, PreviewMagazineData, PreviewMagazineTracker>(scope, data, tracker){

    fun setContent(item:MagazineModelItem){
        data.setFragmentContent(item)
    }
}