package com.nankung.common.module.extension

import android.content.Context
import android.telephony.TelephonyManager

/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */
val Context.isCanUsePhone: Boolean
    get() {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.phoneType != TelephonyManager.PHONE_TYPE_NONE
    }


/**
 * @return
 *
 *  * [TelephonyManager.PHONE_TYPE_NONE] : 0
 *  * [TelephonyManager.PHONE_TYPE_GSM] : 1
 *  * [TelephonyManager.PHONE_TYPE_CDMA] : 2
 *  * [TelephonyManager.PHONE_TYPE_SIP] : 3
 *
 */
val Context.phoneType: Int
    get() {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.phoneType
    }

val Context.isSimCardReady: Boolean
    get() {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.simState == TelephonyManager.SIM_STATE_READY
    }

val Context.simOperatorName: String?
    get() {
        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.simOperatorName
    }


