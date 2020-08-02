package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import com.cristhianbonilla.custom_views.widget.prradio.PRRadioGroupListener
import kotlinx.android.synthetic.main.view_radio_group.view.*

class PRRadioGroup(context: Context, attrs: AttributeSet?) : RadioGroup(context, attrs),
    PRRadioGroupListener {

    private lateinit var onCheckedChangeListener: (String) -> Unit
    private var prRadioGroupListener: PRRadioGroupListener? = null

    init {
        initView()
        this.setOnCheckedChangeListener { buttonView, isChecked ->
            if (this.currentYear.isChecked) {
                print(buttonView)
            } else {
                print(buttonView)
            }
        }

        this.currentYear.setOnClickListener{
            onCheckedChangeListener?.invoke(
                getTextItemSelected()
            )
        }
        this.one_year_ego.setOnClickListener{
            onCheckedChangeListener?.invoke(
                getTextItemSelected()
            )
        }
        this.two_year_ego.setOnClickListener{
            onCheckedChangeListener?.invoke(
                getTextItemSelected()
            )
        }
        this.three_years_ego.setOnClickListener{
            onCheckedChangeListener?.invoke(
                getTextItemSelected()
            )
        }
    }

    private fun initView() {
        Toolbar.inflate(context, R.layout.view_radio_group, this)
    }

    fun getTextItemSelected(): String {
        if (this.currentYear.isChecked) {
            return currentYear.text.toString()
        }

        if (this.one_year_ego.isChecked) {
            return one_year_ego.text.toString()
        }

        if (this.two_year_ego.isChecked) {
            return two_year_ego.text.toString()
        }
        if (this.three_years_ego.isChecked) {
            return three_years_ego.text.toString()
        }
        return "" +
                ""
    }


    override fun clickItem(stringValue: String) {
        prRadioGroupListener?.clickItem(stringValue)
    }

    fun setOnCheckedChangedListener(listener: (String) -> Unit) {
        onCheckedChangeListener = listener
    }

    companion object {
        @BindingAdapter("set_last_years")
        @JvmStatic
        fun setTextRadios(view: PRRadioGroup, lastYears: List<String>) {
            if (lastYears.isNotEmpty()) {
                view.currentYear.text = lastYears[0]
                view.one_year_ego.text = lastYears[1]
                view.two_year_ego.text = lastYears[2]
                view.three_years_ego.text = lastYears[3]
            }
        }

        @BindingAdapter("event_listener")
        @JvmStatic
        fun setItemListener(
            view: PRRadioGroup,
            listener: ((String) -> Unit)?
        ) {
            if(listener!=null){
                view.onCheckedChangeListener = listener
            }
        }
    }

}