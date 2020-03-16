package com.nankung.common.module.extension

/**
 * Created by 「 Nan Kung 」 on 16/3/2563. ^^
 */

fun Int?.nonnull(): Int = this ?: 0
fun Float?.nonnull(): Float = this ?: 0f
fun Long?.nonnull(): Long = this ?: 0L
fun String?.nonnull(): String = this ?: ""
fun Boolean?.nonnull(): Boolean = this ?: false
fun <T> List<T>?.nonnull(): List<T> = this ?: listOf()
fun <A, B> Map<A, B>?.nonnull(): Map<A, B> = this ?: mapOf()