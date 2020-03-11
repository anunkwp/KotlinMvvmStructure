package com.nankung.common.module.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.nankung.common.R


/**
 * Created by TheKhaeng.
 */
class LoadingGradientDialog : LoadingDialog() {

    companion object {

        private fun newInstance(isCancelable: Boolean = false): LoadingGradientDialog {
            val fragment = LoadingGradientDialog()
            fragment.isCancelable = isCancelable
            return fragment
        }
    }


    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.dialog_loading_gradient, container)
    }


    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        setupView()
    }

    private fun bindView(view: View?) {
        // do nothing
    }

    private fun setupView() {
    }

    class Builder {
        fun build(): LoadingGradientDialog {
            return LoadingGradientDialog.newInstance()
        }
    }

}
