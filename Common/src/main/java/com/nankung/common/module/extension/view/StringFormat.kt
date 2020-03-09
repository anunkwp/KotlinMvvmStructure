package com.nankung.common.module.extension.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.IntRange
import androidx.annotation.StringDef
import com.nankung.common.module.extension.util.AppLanguage
import com.nankung.common.module.extension.util.Language
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

@StringDef(KB)
@Retention(AnnotationRetention.SOURCE)
annotation class InternetUnit

@StringDef(TIME)
@Retention(AnnotationRetention.SOURCE)
annotation class SmsUnit

@StringDef(MINUTE, HOUR, DAY)
@Retention(AnnotationRetention.SOURCE)
annotation class VoiceUnit

const val KILO = 1_000L
const val MEGA = 1_000_000L
const val GIGA = 1_000_000_000L
const val TERA = 1_000_000_000_000L
const val PETA = 1_000_000_000_000_000L
const val EXA = 1_000_000_000_000_000_000L

const val SECOND = "SECOND"
const val MINUTE = "MINUTE"
const val HOUR = "HOUR"
const val DAY = "DAY"

const val TIME = "TIME"

const val KB = "KB"

private val suffixes = TreeMap<Long, String>().apply {
    put(KILO, "k")
    put(MEGA, "M")
    put(GIGA, "G")
    put(TERA, "T")
    put(PETA, "P")
    put(EXA, "E")
}


private val timeTh = listOf("นาที", "ชั่วโมง", "วัน")
private val timeThAbbr = listOf("น.", "ช.", "ว.")

private val timeEn = listOf("minute", "hour", "day")
private val timeEnAbbr = listOf("m", "h", "d")

private val smsUnit = mapOf(
        "th" to "ข้อความ",
        "en" to "message")

private val you = mapOf(
        "th" to "คุณ ",
        "en" to "")


const val DEFAULT_SEPARATE = "-"
const val PATTERN_MOBILE_NUMBER = "###-###-####"
const val PATTERN_ID_CARD = "#-####-#####-##-#"
const val PATTERN_CREDIT_CARD = "####-####-####-####"


/* =========================== Time Format ====================================================== */
@SuppressLint("SimpleDateFormat")
fun String.decodeIso8601DateString(format: String = "dd MMMM yyyy", local: Locale = AppLanguage.instance.currentLanguageLocal): String? {
    if (this.decodeIso8601Date() == null) return null
    return SimpleDateFormat(format, local).format(this.decodeIso8601Date())
}

fun String.decodeIso8601DateStringTH(format: String = "dd MMMM yyyy", local: Locale = AppLanguage.instance.languageTHAI): String? {
    if (this.decodeIso8601Date() == null) return null
    return SimpleDateFormat(format, local).format(this.decodeIso8601Date()?.adjustYear(local))
}

fun String.decodeIso8601DateStringEN(format: String = "dd MMMM yyyy", local: Locale = AppLanguage.instance.languageENGLISH): String? {
    if (this.decodeIso8601Date() == null) return null
    return SimpleDateFormat(format, local).format(this.decodeIso8601Date()?.adjustYear(local))
}

fun String.decodeIso8601DateStringAdjustYear(format: String = "dd MMM yy", local: Locale = AppLanguage.instance.currentLanguageLocal): String? {
    if (this.decodeIso8601Date() == null) return null
    return SimpleDateFormat(format, local).format(this.decodeIso8601Date()?.adjustYear(local))
}
fun String.decodeIso8601DateStringAdjustYearPlus(format: String = "dd MMM yy", local: Locale = AppLanguage.instance.currentLanguageLocal): String? {
    if (this.decodeIso8601DatePlus() == null) return null
    return SimpleDateFormat(format, local).format(this.decodeIso8601DatePlus())
}


@SuppressLint("SimpleDateFormat", "LogNotTimber")
fun String.decodeIso8601Date(): Date? {
    if (this.isEmpty()) return null
    val format = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'"
    val readDate = SimpleDateFormat(format)
    return try {
        readDate.parse(this)
    } catch (e: ParseException) {
        Log.e("decodeIso8601Date", format, e)
        null
    }
}
@SuppressLint("SimpleDateFormat")
fun String.decodeIso8601DatePlus(): Date? {
    if (this.isEmpty()) return null
    val format = "yyyy-MM-dd'T'HH:mm"
    val readDate = SimpleDateFormat(format)
    return try {
        readDate.parse(this)
    } catch (e: ParseException) {
        Log.e("decodeIso8601Date", format, e)
        null
    }
}



@SuppressLint("SimpleDateFormat")
fun String.decodeIso8601DateAdjustYear(local: Locale = AppLanguage.instance.currentLocalLanguage): Date? {
    return this.decodeIso8601Date()?.adjustYear(local)
}

fun String.toDate(format: String): Date? {
    if (this.isEmpty()) return null
    val readDate = SimpleDateFormat(format)
    return try {
        readDate.parse(this)
    } catch (e: ParseException) {
        Log.e("toDate", format, e)
        null
    }
}

fun String.toDateAdjustYear(format: String, local: Locale = AppLanguage.instance.currentLanguageLocal): Date? {
    return this.toDate(format)?.adjustYear(local)
}

fun String.toDateString(fromFormat: String, toFormat: String, local: Locale = AppLanguage.instance.currentLocalLanguage): String {
    if (this.isEmpty()) return ""
    val readDate = SimpleDateFormat(toFormat, local)
    val date = this.toDate(fromFormat)
    return try {
        readDate.format(date)
    } catch (e: ParseException) {
        Log.e("toDateString", toFormat, e)
        ""
    }
}

fun String.toDateStringAdjustYear(fromFormat: String, toFormat: String, local: Locale = AppLanguage.instance.currentLanguageLocal): String {
    if (this.isEmpty()) return ""
    val readDate = SimpleDateFormat(toFormat, local)
    val date = this.toDateAdjustYear(fromFormat, local)
    return try {
        readDate.format(date)
    } catch (e: ParseException) {
        Log.e("toDateString", toFormat, e)
        ""
    }
}

fun Date.adjustYear(local: Locale): Date {
    return if (local.toString().toLowerCase() == AppLanguage.THAI) {
        Calendar.getInstance().apply {
            time = this@adjustYear
            year += 543
        }.time
    } else {
        this
    }
}


/* =========================== Default Format =================================================== */
fun String.you(@Language language: String): String = you[language] + this

fun Int.baht(): String {
    val df = getDecimalFormat("#,###,###", 0)
    return "฿" + df.format(this)
}

fun Float.baht(): String {
    val df = getDecimalFormat("#,###,##0", 0)
    return "฿" + df.format(this)
}

fun unit(unit: String): String{
    return " $unit"
}


fun Int.net(): String {
    val df = getDecimalFormat("#,###,###", 0)
    return df.format(this)
}

fun Float.net(): String {
    val df = getDecimalFormat("#,###,##0", 0)
    return df.format(this)
}



fun String.phone() = this.applyStringPattern(PATTERN_MOBILE_NUMBER)

fun Double.internet(): String {
    return if (this % 1 == 0.toDouble()) {
        this.toInt().toString()
    } else {
        String.format("%.2f", this)
    }
}


fun Int.internetSpeed(@InternetUnit unit: String): String {
    if (this == 0) return "0 kbps"

    var valueSuffix = 1L
    if (KB == unit) {
        valueSuffix *= KILO
    }
    val tmpSpeed: Long = this.toLong()

    val e = suffixes.floorEntry(tmpSpeed * valueSuffix)
    val divideBy = e?.key
    val suffix = e?.value

    val newValue = divideBy?.let { tmpSpeed * valueSuffix / divideBy } ?: 0
    val df = getDecimalFormat("#", 0)
    return df.format(newValue) + " " + suffix + "bps"
}

fun Int.sms(@SmsUnit unit: String, @Language language: String): String {
    if (this == 0) return "0 " + getUnitSms(language)

    val tmpSms: Long = this.toLong()
    val tmpUnit: String =
            if (TIME == unit) {
                getUnitSms(language)
            } else {
                ""
            }

    return tmpSms.toString() + " " + tmpUnit
}

fun Int.voice(@VoiceUnit unit: String): String {
    val tmpVoice: Long =
            if (SECOND == unit) {
                this.toLong() / 60
            } else {
                this.toLong()
            }
    return tmpVoice.toString()
}

fun String.creditCard(): String = this.applyStringPattern(PATTERN_CREDIT_CARD)

fun String.applyStringPattern(format: String,
                              separate: String = DEFAULT_SEPARATE): String {
    // ex. pattern "(\\d{3})(\\d{3})(\\d+)"
    var pattern = ""
    // ex. replacement "$1-$2-$3"
    var replacement = ""
    val formats = format
            .split(separate.toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()

    formats.indices.forEach { i ->
        pattern += "([0-9a-zA-Z]{" + formats[i].length + "})"
        replacement += if (i == 0) {
            "$" + (i + 1)
        } else {
            separate + "$" + (i + 1)
        }
    }

    return this.replaceFirst(pattern.toRegex(), replacement)
}


/* =========================== Private method =============================================== */
private fun getUnitSms(@Language language: String): String {
    return smsUnit[language] ?: ""
}

private fun getUnitMinute(@Language language: String): String {
    return getUnitTime(language, 0)
}

private fun getUnitHour(@Language language: String): String {
    return getUnitTime(language, 1)
}

private fun getUnitDay(@Language language: String): String {
    return getUnitTime(language, 2)
}

private fun getUnitTime(@Language language: String,
                        @IntRange(from = 0, to = 2) index: Int): String {
    when (language) {
        AppLanguage.ENGLISH -> {
            return timeEn[index]
        }
        AppLanguage.THAI -> {
            return timeTh[index]
        }
    }
    return timeEn[index]
}


private fun getDecimalFormat(preFormat: String, point: Int): DecimalFormat {
    return DecimalFormat(getFormat(preFormat, point)).apply {
        roundingMode = RoundingMode.HALF_UP
    }
}

private fun getFormat(preFormat: String, point: Int): String {
    var tmpFormat = preFormat
    for (i in 0 until point) {
        if (i == 0) tmpFormat += "."
        tmpFormat += "#"
    }
    return tmpFormat
}



