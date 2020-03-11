package com.nankung.common.module.dialog

import android.os.Bundle
import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.nankung.common.R
import com.nankung.common.module.extension.view.hide
import com.nankung.common.module.extension.view.show


/**
 * Created by TheKhaeng.
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    SINGLE_NORMAL,
    SINGLE_GRADIENT,
    CHOICE_NORMAL,
    CHOICE_REVERSE
)
annotation class DialogMode

const val SINGLE_NORMAL = 1
const val SINGLE_GRADIENT = 2
const val CHOICE_NORMAL = 3
const val CHOICE_REVERSE = 4

class SimpleDialog : DialogFragment() {

    companion object {

        private const val KEY_MODE = "key_mode"
        private const val KEY_TITLE = "key_title"
        private const val KEY_MESSAGE = "key_message"
        private const val KEY_POSITIVE = "key_positive"
        private const val KEY_NEGATIVE = "key_negative"

        private const val KEY_TITLE_ID = "key_title_id"
        private const val KEY_MESSAGE_ID = "key_message_id"
        private const val KEY_POSITIVE_ID = "key_positive_id"
        private const val KEY_NEGATIVE_ID = "key_negative_id"
        private const val KEY_DATA = "key_data"

        private const val NO_ID = -1

        private fun newInstance(
            @DialogMode mode: Int,
            positive: String,
            negative: String,
            title: String,
            message: String,
            @StringRes positiveId: Int,
            @StringRes negativeId: Int,
            @StringRes titleId: Int,
            @StringRes messageId: Int,
            data: Bundle?,
            isCancelable: Boolean = false): SimpleDialog {
            val fragment = SimpleDialog()
            val bundle = Bundle()
            bundle.putInt(KEY_MODE, mode)
            bundle.putString(KEY_TITLE, title)
            bundle.putString(KEY_MESSAGE, message)
            bundle.putString(KEY_POSITIVE, positive)
            bundle.putString(KEY_NEGATIVE, negative)
            bundle.putInt(KEY_TITLE_ID, titleId)
            bundle.putInt(KEY_MESSAGE_ID, messageId)
            bundle.putInt(KEY_POSITIVE_ID, positiveId)
            bundle.putInt(KEY_NEGATIVE_ID, negativeId)
            bundle.putBundle(KEY_DATA, data)
            fragment.isCancelable = isCancelable
            fragment.arguments = bundle
            return fragment
        }
    }


    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvMessage: AppCompatTextView
    private lateinit var btnOk: AppCompatButton
    private lateinit var btnCancel: AppCompatButton

    val mode: Int get() = arguments?.getInt(KEY_MODE) ?: SINGLE_GRADIENT
    val title: String get() = arguments?.getString(KEY_TITLE) ?: ""
    val message: String get() = arguments?.getString(KEY_MESSAGE) ?: ""
    val positive: String get() = arguments?.getString(KEY_POSITIVE) ?: ""
    val negative: String get() = arguments?.getString(KEY_NEGATIVE) ?: ""
    val titleId: Int get() = arguments?.getInt(KEY_TITLE_ID) ?: NO_ID
    val messageId: Int get() = arguments?.getInt(KEY_MESSAGE_ID) ?: NO_ID
    val positiveId: Int get() = arguments?.getInt(KEY_POSITIVE_ID) ?: NO_ID
    val negativeId: Int get() = arguments?.getInt(KEY_NEGATIVE_ID) ?: NO_ID
    val data: Bundle? get() = arguments?.getBundle(KEY_DATA)
    private var positiveListener: PositiveClickListener? = null
    private var negativeListener: NegativeClickListener? = null

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.dialog_simple, container)
    }


    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        setupView()
    }

    private fun bindView(view: View?) {
        view?.let {
            tvTitle = it.findViewById(R.id.dialog_simple_title) as AppCompatTextView
            tvMessage = it.findViewById(R.id.dialog_simple_message) as AppCompatTextView
            btnOk = it.findViewById(R.id.dialog_simple_button_ok) as AppCompatButton
            btnCancel = it.findViewById(R.id.dialog_simple_button_cancel) as AppCompatButton
        }
    }

    private fun setupView() {
        tvTitle.show(title.isEmpty())
        tvMessage.show(message.isEmpty())

        setText(tvTitle, title, titleId)
        setText(tvMessage, message, messageId)

        setText(btnCancel, negative, negativeId)
        setText(btnOk, positive, positiveId)

        btnOk.setOnClickListener { dismiss() }

        btnOk.setOnClickListener {
            positiveListener?.onClick(btnCancel, data)
            dismiss()
        }

        btnCancel.setOnClickListener {
            negativeListener?.onClick(btnOk, data)
            dismiss()
        }

        when (mode) {
            SINGLE_NORMAL -> {
                btnOk.show()
                btnCancel.hide()
            }
            SINGLE_GRADIENT -> {
                btnOk.hide()
                btnCancel.show()
            }
            CHOICE_NORMAL -> {
                btnOk.show()
                btnCancel.show()
            }
            CHOICE_REVERSE -> {
                btnOk.hide()
                btnCancel.show()
            }
        }
    }

    private fun setText(tv: TextView, string: String, @StringRes stringId: Int) {
        tv.show()
        when {
            string.isNotEmpty() -> tv.text = string
            stringId != NO_ID -> tv.text = context?.getString(stringId)
            else -> tv.hide()
        }
    }

    private fun setPositiveListener(positiveListener: PositiveClickListener?) {
        this.positiveListener = positiveListener
    }

    private fun setNegativeListener(negativeListener: NegativeClickListener?) {
        this.negativeListener = negativeListener
    }


    class Builder {
        private var mode = SINGLE_GRADIENT
        private var positive: String = ""
        private var negative: String = ""
        private var title: String = ""
        private var message: String = ""
        private var positiveId: Int =
            NO_ID
        private var negativeId: Int =
            NO_ID
        private var titleId: Int =
            NO_ID
        private var messageId: Int =
            NO_ID
        private var data: Bundle? = null
        private var negativeListener: NegativeClickListener? = null
        private var positiveListener: PositiveClickListener? = null


        fun setMode(@DialogMode mode: Int): Builder {
            this.mode = mode
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setTitle(@StringRes title: Int): Builder {
            this.titleId = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setMessage(@StringRes message: Int): Builder {
            this.messageId = message
            return this
        }

        fun setData(data: Bundle?): Builder {
            this.data = data
            return this
        }

        fun setPositiveListener(positive: String, listener: PositiveClickListener? = null): Builder {
            this.positive = positive
            this.positiveListener = listener
            return this
        }

        fun setPositiveListener(@StringRes positive: Int, listener: PositiveClickListener? = null): Builder {
            this.positiveId = positive
            this.positiveListener = listener
            return this
        }

        fun setNegativeListener(negative: String, listener: NegativeClickListener? = null): Builder {
            this.negative = negative
            this.negativeListener = listener
            return this
        }

        fun setNegativeListener(@StringRes negative: Int, listener: NegativeClickListener? = null): Builder {
            this.negativeId = negative
            this.negativeListener = listener
            return this
        }

        fun build(): SimpleDialog {
            val dialog =
                newInstance(
                    mode,
                    positive,
                    negative,
                    title,
                    message,
                    positiveId,
                    negativeId,
                    titleId,
                    messageId,
                    data
                )
            dialog.setNegativeListener(negativeListener)
            dialog.setPositiveListener(positiveListener)
            return dialog
        }
    }
}

