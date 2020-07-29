package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.execute

class HomeViewModel(
    scope: Scope,
    data: HomeData,
    tracker: HomeTracker,
    private val getKeyWordsUseCase: GetKeyWordsUseCase
) : BaseViewModel<HomeMagazineState, HomeData, HomeTracker>(scope, data, tracker) {


    fun getKeyWord(){
        data.loading()
        execute { getKeyWordsUseCase(UseCase.None).fold(
            {
                data.error()
            }, ::handleKeyWords
        ) }
    }

    private fun handleKeyWords(keywords: KeyWordModel){
        val keyWordsString = ArrayList<String>()
        keywords.keywordList.forEach {
            keyWordsString.add(it.name)
        }
        data.submitKeyWords(keyWordsString)
    }

}