package com.nankung.common.module.creator

import com.nankung.common.R

/**
 * Created by 「 Nan Kung 」 on 17/3/2563. ^^
 */
object ViewCreator {


    private const val VIEW_TYPE_0: Int = 0
    private const val VIEW_TYPE_1: Int = 1
    private const val VIEW_TYPE_2: Int = 2
    private const val VIEW_TYPE_3: Int = 3
    private const val VIEW_TYPE_4: Int = 4


    fun getColorActiveCreator(packageType: Int): Int {
        return when (packageType) {
            VIEW_TYPE_0 -> R.color.base_black_100_percent
            VIEW_TYPE_1 -> R.color.base_white_100_percent
            else -> R.color.colorPrimaryDark
        }
    }
}