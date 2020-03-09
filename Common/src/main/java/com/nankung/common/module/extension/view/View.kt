package com.nankung.common.module.extension.view

import android.annotation.SuppressLint
import android.view.View

/**
  * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

@SuppressLint("ClickableViewAccessibility")


fun View?.show(isShow: Boolean?) {
    this?.visibility = if (isShow == true) View.VISIBLE else View.GONE
}

fun View?.isShow(): Boolean {
    return this?.visibility == View.VISIBLE
}

fun View?.show() {
    if (this?.visibility != View.VISIBLE) {
        this?.visibility = View.VISIBLE
    }
}

fun View?.hide() {
    if (this?.visibility != View.GONE) {
        this?.visibility = View.GONE
    }
}

fun View?.invisible() {
    if (this?.visibility != View.INVISIBLE) {
        this?.visibility = View.INVISIBLE
    }
}




