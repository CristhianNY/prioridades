package com.cristhianbonilla.custom_views.widget.creditCard


import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class CreditCardExpirationMonthDateWatcher : TextWatcher {
    private var yearEdit: EditText
    private var tvYear: TextView? = null
    private var isDelete = false

    constructor(yearEdit: EditText, yearTv: TextView?) {
        this.yearEdit = yearEdit
        tvYear = yearTv
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        isDelete = before != 0
    }

    override fun afterTextChanged(s: Editable) {
        val source = s.toString()
        var length = source.length

        if (tvYear != null) {
            if (length == 0) {
                tvYear!!.text = "XXXX"
            } else {
                tvYear!!.text =
                    source
            }
        }
    }

    interface CreditCardType {
        fun setCardType(type: Int)
    }
}