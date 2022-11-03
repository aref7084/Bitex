package com.hope.bitex.util

import android.content.res.Resources
import androidx.annotation.IntRange


fun getDimens(): Pair<Int, Int> {
    val displayMetrics = Resources.getSystem().displayMetrics
    return Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)

}

fun getPercentWidth(@IntRange(from = 1L, to = 100L) percent: Int): Int {
    return getDimens().first * percent / 100
}


fun getPercentHeight(@IntRange(from = 1L, to = 100L) percent: Int): Int {
    return getDimens().second * percent / 100
}


