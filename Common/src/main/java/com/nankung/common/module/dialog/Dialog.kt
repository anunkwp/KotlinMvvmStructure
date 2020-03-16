package com.nankung.common.module.dialog

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showChoiceDialog(title: String? = null, message: String, positiveClickListener: PositiveClickListener? = null, negativeClickListener: NegativeClickListener? = null) =
    DialogManager.showDialog(
        this.supportFragmentManager,
        title,
        message,
        positiveClickListener = positiveClickListener,
        negativeClickListener = negativeClickListener
    )
fun Fragment.showChoiceDialog(title: String? = null, message: String, positiveClickListener: PositiveClickListener?= null, negativeClickListener: NegativeClickListener? = null) = DialogManager.showDialog(this.childFragmentManager, title, message, positiveClickListener = positiveClickListener, negativeClickListener = negativeClickListener)

fun FragmentActivity.showGradientLoading() = DialogManager.showLoadingGradientDialog(this.supportFragmentManager)
fun Fragment.showGradientLoading() = DialogManager.showLoadingGradientDialog(this.childFragmentManager)

fun FragmentActivity.hideLoading() = DialogManager.dismissLoadingDialog(this.supportFragmentManager)
fun Fragment.hideLoading() = DialogManager.dismissLoadingDialog(this.childFragmentManager)
