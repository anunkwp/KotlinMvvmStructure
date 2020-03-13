package com.nankung.common.module.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.nankung.common.R


/**
 * Created by 「 Nan Kung 」 on 13/3/2563. ^^
 */

class AppEdittext : AppCompatEditText {
    var mClearButtonImage: Drawable? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_close, null)
        setOnTouchListener(OnTouchListener { _, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClearButtonClicked = false
                if (layoutDirection == LAYOUT_DIRECTION_RTL) {
                    clearButtonEnd = mClearButtonImage!!.intrinsicWidth + paddingStart.toFloat()
                    if (event.x < clearButtonEnd) {
                        isClearButtonClicked = true
                    }
                } else {
                    clearButtonStart = (width - paddingEnd
                            - mClearButtonImage!!.intrinsicWidth).toFloat()
                    if (event.x > clearButtonStart) {
                        isClearButtonClicked = true
                    }
                }
                if (isClearButtonClicked) {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        mClearButtonImage = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_close, null
                        )
                        showClearButton()
                    }
                    if (event.action == MotionEvent.ACTION_UP) {
                        mClearButtonImage = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_close, null
                        )
                        text!!.clear()
                        hideClearButton()
                        return@OnTouchListener true
                    }
                } else {
                    return@OnTouchListener false
                }
            }
            false
        })
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { showClearButton() }
            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
    }
}