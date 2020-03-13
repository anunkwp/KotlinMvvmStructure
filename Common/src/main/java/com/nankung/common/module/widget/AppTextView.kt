package com.nankung.common.module.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.nankung.common.R

/**
 * Created by 「 Nan Kung 」 on 13/3/2563. ^^
 */
class AppTextView : AppCompatTextView {
    private var enableColor = -1

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        //จะ Custom ไปแนวไหน แล้วแต่งาน มาทำตรงนี้
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            setTextColor(enableColor)
        } else {
            setTextColor(ContextCompat.getColor(context, R.color.base_white_100_percent))
        }
    }
}