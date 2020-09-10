package com.cristhianbonilla.features_home.ui.home

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.cristhianbonilla.custom_views.widget.prradio.PRRadioGroupListener
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    scope: Scope,
    data: HomeData,
    tracker: HomeTracker,
    private val getKeyWordsUseCase: GetKeyWordsUseCase,
    private val getMagazineListUseCase: GetMagazineListUseCase
) : BaseViewModel<HomeMagazineState, HomeData, HomeTracker>(scope, data, tracker) {

    var magazineArrayList: ArrayList<MagazineModelItem> = ArrayList()

    val impl = object : PRRadioGroupListener {
        override fun clickItem(stringValue: String) {
            Toast.makeText(context, stringValue, Toast.LENGTH_LONG).show()
        }
    }

    val onYearItemClick: (String) -> Unit = { clickType ->
        Toast.makeText(context, clickType, Toast.LENGTH_LONG).show()
    }

    val magazineItemClick: (MagazineModelItem) -> Unit = { itemClicked ->
        data.onMagazineItemClicked(itemClicked)
    }

    var yearListener: MutableLiveData<PRRadioGroupListener> = MutableLiveData()

    val goToSearch = {
        data.updateStateToSearch()
    }

    fun getKeyWord() {
        execute {
            getKeyWordsUseCase(UseCase.None).fold(
                {
                    data.error()
                }, ::handleKeyWords
            )
        }
    }

    fun getMagazineList(year: String, keyWord: String) {
        data.loading()
        execute {
            getMagazineListUseCase(GetMagazineListUseCase.Params(year, keyWord)).fold(
                { handleGetMagazineFailure() },
                ::handleMagazineListSuccess
            )
        }
    }

    private fun handleGetMagazineFailure() {
        data.error()
        data.showEmptyState()
    }

    private fun handleMagazineListSuccess(magazineList: MagazineModel) {
        data.success()
        magazineArrayList.clear()
        for (magazine in magazineList.magazineList) {

            magazineArrayList.add(magazine)
        }
        data.submitMagazineList(magazineArrayList)
        data.hideEmptyState()


    }

    fun savedMagazineList(magazineList: ArrayList<MagazineModelItem>) {
        data.submitMagazineList(magazineList)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleKeyWords(keywords: KeyWordModel) {
        val keyWordsString = ArrayList<String>()
        keywords.keywordList.forEach {
            keyWordsString.add(it.name)
        }

        val years = ArrayList<String>()

        for (year in 1930 until Calendar.getInstance().get(Calendar.YEAR) + 1) {
            years.add(year.toString())
        }

        keyWordsString.removeIf(String::isEmpty)

        keyWordsString.add(0, "Palabra clave")
        data.submitKeyWords(keyWordsString)
        data.submitYearsList(years.reversed())
        data.submitLastFourYear()
        yearListener.postValue(impl)
    }
}