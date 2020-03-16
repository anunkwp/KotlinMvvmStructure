package com.nankung.common.module.extension

import androidx.annotation.IntDef
import java.util.regex.Pattern

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */

const val VALIDATE = 0x0001
const val INVALIDATE = 0x0002
const val INVALIDATE_EMPTY = 0x0010 or INVALIDATE

const val INVALIDATE_MOBILE_NUMBER_FORMAT = 0x0100 or INVALIDATE

const val INVALIDATE_EMAIL_FORMAT = 0x1000 or INVALIDATE

@IntDef(VALIDATE,
    INVALIDATE,
    INVALIDATE_EMPTY,
    INVALIDATE_MOBILE_NUMBER_FORMAT,
    INVALIDATE_EMAIL_FORMAT)
@Retention(AnnotationRetention.SOURCE)
annotation class ValidateString

fun String.validateEmpty(): Int {
    if (this.isEmpty()) return INVALIDATE_EMPTY
    return VALIDATE
}

fun String.validateEmail(): Int {
    if (this.isEmpty()) return INVALIDATE_EMPTY
    if (this.isNotEmail()) return INVALIDATE_EMAIL_FORMAT
    return VALIDATE
}

fun String.validateMobileNumber(): Int {
    if (this.isEmpty()) return INVALIDATE_EMPTY
    if (this.isNotMobileNumber()) return INVALIDATE_MOBILE_NUMBER_FORMAT
    return VALIDATE
}


fun @receiver:ValidateString Int.isValidate(): Boolean {
    return this and VALIDATE == VALIDATE
}

fun @receiver:ValidateString Int.isInvalidate(): Boolean {
    return this and INVALIDATE == INVALIDATE
}


fun String?.isEmail(): Boolean {
    if (this != null) {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return emailPattern.matcher(this).matches()
    }
    return false
}

fun String?.isNotEmail(): Boolean {
    return !this.isEmail()
}

fun String?.isMobileNumber(): Boolean {
    return this != null && this.length == 10 && this[0] == '0'
}

fun String?.isNotMobileNumber(): Boolean {
    return !this.isMobileNumber()
}