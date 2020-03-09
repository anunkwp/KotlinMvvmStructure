package com.nankung.common.module.extension.util

import androidx.annotation.StringDef
import java.util.*


/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

@StringDef(AppLanguage.THAI, AppLanguage.ENGLISH)
@Retention(AnnotationRetention.SOURCE)
annotation class Language

class AppLanguage {

    private object Holder {
        val INSTANCE = AppLanguage()
    }

    companion object {

        const val THAI = "th"
        const val ENGLISH = "en"

        const val DEFAULT_LANGUAGE = THAI

        val instance: AppLanguage by lazy { Holder.INSTANCE }

    }

    var currentLanguage = THAI
    var languageTHAI: Locale = Locale(THAI)
    var languageENGLISH: Locale = Locale(ENGLISH)
    var currentLanguageHeader = getLanguageDevice()
    var currentLanguageLocal:Locale = Locale(currentLanguageHeader)
    val currentLocalLanguage: Locale
        get() = Locale(currentLanguage)

    val isThai: Boolean
        get() = currentLanguage.equals(THAI, ignoreCase = true)

    val isEnglish: Boolean
        get() = currentLanguage.equals(ENGLISH, ignoreCase = true)

    private fun getLanguageDevice(): String {
        return if (THAI == Locale.getDefault().language) THAI else ENGLISH
    }

}
