package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import kotlinx.android.synthetic.main.view_pr_empty_state.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import kotlin.properties.Delegates


@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class PREmptyState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var type: Int? = null

    private var linkEmptyState by Delegates.observable<CharSequence>("") { _, _, text ->
        btnEmptyStateLink.text = text
        btnEmptyStateLink.isVisible = text.isNotEmpty()
    }

    private var errorHeader by Delegates.observable<CharSequence>("") { _, _, text ->
        lbEmptyStateErrorHeader.text = text
        lbEmptyStateErrorHeader.isVisible = text.isNotEmpty()
    }

    init {
        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet?) {
        initView()
        loadAttrs(attrs)
        style()
    }

    private fun style() {
        when (type) {
            1 -> {
                btnEmptyStateLink.visibility = View.GONE
            }
        }
    }

    private fun loadAttrs(attrs: AttributeSet?) {
        with(context.obtainStyledAttributes(attrs, R.styleable.PREmptyState, 0, 0)) {

            type = getInt(R.styleable.PREmptyState_empty_state_type, 0)

            lbEmptyStateMessage.text = resources.getText(
                getResourceId(R.styleable.PREmptyState_emptystate_message, -1), ""
            )

            ivEmptyStateIcon.setImageResource(
                getResourceId(
                    R.styleable.PREmptyState_emptystate_icon,
                    R.drawable.ic_error_state
                )
            )

            linkEmptyState = resources.getText(
                getResourceId(R.styleable.PREmptyState_emptystate_link, -1), ""
            )

            errorHeader = resources.getText(
                getResourceId(R.styleable.PREmptyState_emptystate_errorHeader, -1), ""
            )

            recycle()
        }
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.view_pr_empty_state, this, true)
    }

    fun setMessageBody(text: String): PREmptyState {
        lbEmptyStateMessage.text = text
        return this
    }

    fun setLinkText(text: String): PREmptyState {
        linkEmptyState = text
        return this
    }

    fun setErrorHeader(text: String): PREmptyState {
        errorHeader = text
        return this
    }

    fun setOnClickListenerLink(action: () -> Unit): PREmptyState {
        btnEmptyStateLink.setOnClickListener { action() }
        return this
    }

    fun setIconResource(res: Int): PREmptyState {
        ivEmptyStateIcon.setImageResource(res)
        return this
    }

    fun setEmptyState(state: EmptyStates, action: (() -> Unit)? = null): PREmptyState {
        errorHeader = state.header?.let { resources.getString(it) } ?: ""
        linkEmptyState = state.link?.let { resources.getString(it) } ?: ""
        setMessageBody(resources.getString(state.text))
        setIconResource(state.icon)
        action?.let { setOnClickListenerLink(it) }
        return this
    }

    enum class EmptyStates(
        val icon: Int,
        val header: Int? = null,
        val text: Int,
        val link: Int? = null
    ) {
        CONNECTION_ARGENTINA(
            R.drawable.ic_error_state,
            R.string.connection_error_text,
            R.string.connection_error_text,
            R.string.connection_error_text
        ),
        NO_ITEMS(R.drawable.ic_error_state, text = R.string.connection_error_text),
        NO_RESULTS(R.drawable.ic_error_state, text = R.string.no_existen_revistas_para_mostrar),
        NO_MAGAZINES(R.drawable.ic_error_state, text = R.string.no_existen_revistas_para_mostrar),
        NO_LOGIN(R.drawable.ic_error_state, text = R.string.login_error)
    }

    companion object {

        @BindingAdapter("emptystate_message")
        @JvmStatic
        fun setEmptyStateMessage(view: PREmptyState, text: String) {
            view.setMessageBody(text)
        }

        @BindingAdapter("emptystate_message")
        @JvmStatic
        fun setEmptyStateMessage(view: PREmptyState, @StringRes text: Int) {
            view.setMessageBody(view.resources.getString(text))
        }

        @BindingAdapter("emptystate_icon")
        @JvmStatic
        fun setEmptyStateIcon(view: PREmptyState, res: Int) {
            view.setIconResource(res)
        }

        @BindingAdapter("emptystate_link")
        @JvmStatic
        fun setEmptyStateLink(view: PREmptyState, text: String) {
            view.setLinkText(text)
        }

        @BindingAdapter("emptystate_errorHeader")
        @JvmStatic
        fun setEmptyStateErrorHeader(view: PREmptyState, text: String) {
            view.setErrorHeader(text)
        }

        @BindingAdapter("emptystate_setState")
        @JvmStatic
        fun setEmptyState(view: PREmptyState, state: EmptyStates) {
            view.setEmptyState(state)
        }

        @BindingAdapter("emptystate_onClickLink")
        @JvmStatic
        fun onClickLinked(view: PREmptyState, onClickLink: () -> Unit) {
            view.setOnClickListenerLink(onClickLink)
        }
    }
}