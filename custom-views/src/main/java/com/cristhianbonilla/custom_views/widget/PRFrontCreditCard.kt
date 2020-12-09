package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import com.cristhianbonilla.custom_views.widget.creditCard.*
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardFormattingTextWatcher.CreditCardType
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardUtils.AMEX
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardUtils.DISCOVER
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardUtils.MASTERCARD
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardUtils.NONE
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardUtils.VISA
import com.cristhianbonilla.domain.model.countries.CountryItemModel
import kotlinx.android.synthetic.main.view_credit_card.view.*


class PRFrontCreditCard : ConstraintLayout {

    private var onPayBtnListener: ((creditCardData: CreditCardData) -> Unit)? =
        null

    private var creditCardType: String = ""

    private var countryCard: String = "CO"

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    init {
        initView()
        formatTextCreditCard()
    }

    fun setPayListener(payListener: ((creditCarddata: CreditCardData) -> Unit)) {
        this.onPayBtnListener = payListener

    }

    private fun formatTextCreditCard() {
        tv_number.text = "XXXX XXXX XXXX XXXX"
        et_number.addTextChangedListener(
            CreditCardFormattingTextWatcher(
                et_number,
                tv_number,
                object : CreditCardType {
                    override fun setCardType(type: Int) {

                        when (type) {
                            VISA -> {
                                creditCardType = VISA_CARD
                                setCreditCardStyle(
                                    R.drawable.ic_cardvisa,
                                    R.drawable.visa_background
                                )
                            }

                            MASTERCARD -> {
                                creditCardType = MASTERCARD_CARD
                                setCreditCardStyle(
                                    R.drawable.ic_cardmastercard,
                                    R.drawable.master_card_background
                                )
                            }

                            AMEX -> {
                                creditCardType = AMERICAN_EXPRESS_CARD
                                setCreditCardStyle(
                                    R.drawable.ic_cardamex,
                                    R.drawable.amex_background
                                )
                            }
                            DISCOVER -> {
                                creditCardType = DISCOVER_CARD
                                setCreditCardStyle(
                                    R.drawable.ic_caddiscover,
                                    R.drawable.discover_background
                                )
                            }

                            NONE -> {
                                setCreditCardStyle(
                                    0,
                                    0
                                )
                            }
                        }
                    }
                })
        )

        edit_month_card.addTextChangedListener(
            CreditCardExpirationMonthDateWatcher(
                edit_month_card,
                tv_year_card
            )
        )

        edit_expiration_year.addTextChangedListener(
            CreditCardExpirationYearDateWatcher(
                edit_expiration_year,
                tv_month_card
            )
        )
        nameInCard.addTextChangedListener(CreditCardNameWatcher(nameInCard, tv_card_name))

        edit_cvv.addTextChangedListener(CreditCardNameWatcher(edit_cvv, tv_cvv))

        nextStepCard.setOnClickListener {

            val creditCardData = CreditCardData(
                et_number.text.toString().replace("\\s".toRegex(), ""),
                edit_expiration_year.text.toString().plus("/")
                    .plus(edit_month_card.text.toString()),
                nameInCard.text.toString(),
                edit_cvv.text.toString(),
                countryCard, creditCardType
            )
            onPayBtnListener?.invoke(
                creditCardData
            )
        }

        spinnerCountry.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
          val country =    parent?.getItemAtPosition(position) as CountryItemModel
                countryCard = country.code
            }
        }

    }

    private fun setCreditCardStyle(icCard: Int, visaBackground: Int) {
        ivCreditCard.setBackgroundResource(icCard)
        creditCardContainer.setBackgroundResource(visaBackground)
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_credit_card, this, true)
    }


    companion object {
        @BindingAdapter("payment_listener")
        @JvmStatic
        fun setPaymentListener(
            view: PRFrontCreditCard,
            payListener: ((creditCardData: CreditCardData) -> Unit)
        ) {
            view.setPayListener(payListener)
        }

        @BindingAdapter("setCountries")
        @JvmStatic
        fun setCountries(
            view: PRFrontCreditCard,
            listCountries: List<CountryItemModel>
        ) {
            view.spinnerCountry.setCountries(listCountries)
        }

        const val VISA_CARD = "VISA"
        const val MASTERCARD_CARD = "MASTERCARD"
        const val AMERICAN_EXPRESS_CARD = "AMEX"
        const val DISCOVER_CARD = "DISCOVER"

    }

}