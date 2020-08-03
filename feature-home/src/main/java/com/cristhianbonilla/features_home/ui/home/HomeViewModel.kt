package com.cristhianbonilla.features_home.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.cristhianbonilla.custom_views.widget.prradio.PRRadioGroupListener
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute

class HomeViewModel(
    scope: Scope,
    data: HomeData,
    tracker: HomeTracker,
    private val getKeyWordsUseCase: GetKeyWordsUseCase,
    private val getMagazineListUseCase: GetMagazineListUseCase
) : BaseViewModel<HomeMagazineState, HomeData, HomeTracker>(scope, data, tracker){

    val impl = object : PRRadioGroupListener {
        override fun clickItem(stringValue: String) {
            Toast.makeText(context, stringValue, Toast.LENGTH_LONG).show()
        }
    }

    val onYearItemClick: (String) -> Unit = { clickType ->
        Toast.makeText(context, clickType, Toast.LENGTH_LONG).show()
    }


    var yearListener: MutableLiveData<PRRadioGroupListener> = MutableLiveData()

    fun getKeyWord() {
        data.loading()
        execute {
            getKeyWordsUseCase(UseCase.None).fold(
                {
                    data.error()
                }, ::handleKeyWords
            )
        }
    }

    fun getMagazineList(){
        data.loading()

        execute {
            getMagazineListUseCase(GetMagazineListUseCase.Params("2020","finanzas")).fold(
                { handleGetMagazineFailure() },
                ::handleMagazineListSuccess
            )
        }
    }

    private fun handleGetMagazineFailure() {
        data.error()
        Log.d("Error al traer revisas", "Error Al traer magazines")
    }

    private fun handleMagazineListSuccess(magazineList:MagazineModel){
        data.submitMagazineList(magazineList.magazineList)
    }

    private fun handleKeyWords(keywords: KeyWordModel) {
        val keyWordsString = ArrayList<String>()
        keywords.keywordList.forEach {
            keyWordsString.add(it.name)
        }
        data.submitKeyWords(keyWordsString)
        data.submitLastFourYear()
        yearListener.postValue(impl)
    }
}