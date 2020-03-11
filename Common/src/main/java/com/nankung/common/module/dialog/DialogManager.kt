package com.nankung.common.module.dialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.nankung.common.R
import timber.log.Timber

object DialogManager {

    private val TAG_DIALOG = "tag_dialog"
    private var dialog: DialogFragment? = null
    private var dialogLoading: DialogFragment? = null
    private val TAG_LOADING_DIALOG = "tag_loading_dialog"

    fun showDialog(fragmentManager: FragmentManager,
                   title: String?,
                   message: String?,
                   positiveResString: Int = R.string.text_ok,
                   negativeResString: Int = R.string.text_cancel,
                   positiveClickListener: PositiveClickListener?,
                   negativeClickListener: NegativeClickListener?) {
        dismissDialog()
        try {
            dialog = SimpleDialog.Builder()
                .setMode(CHOICE_NORMAL)
                .apply {
                    title?.let { setTitle(it) }
                    message?.let { setMessage(it) }
                }
                .setPositiveListener(positiveResString, positiveClickListener)
                .setNegativeListener(negativeResString, negativeClickListener)
                .build()
                .apply {
                    show(fragmentManager,
                        TAG_DIALOG
                    )
                }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun showLoadingGradientDialog(fragmentManager: FragmentManager) {
            if (!isLoadingDialogAdded(fragmentManager)) {
                try {
                    dialogLoading = LoadingGradientDialog.Builder()
                        .build()
                        .apply {
                            show(fragmentManager, TAG_LOADING_DIALOG)
                        }
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
    }
     fun dismissDialog() {
        try {
            if (dialog?.isAdded == true) {
                dialog?.dismiss()
                dialog = null
            }

            if (dialogLoading?.isAdded == true) {
                dialogLoading?.dismiss()
                dialogLoading = null
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    fun isLoadingDialogAdded(manager: FragmentManager): Boolean {
        val fragments = manager.fragments
        for (fragment in fragments) {
            if (fragment is LoadingDialog) {
                return true
            }
        }
        return false
    }

    fun dismissLoadingDialog(manager: FragmentManager) {
        val fragments = manager.fragments
        for (fragment in fragments) {
            if (fragment is LoadingDialog) {
                fragment.dismissAllowingStateLoss()
                dialogLoading = null
            }
        }
    }





}