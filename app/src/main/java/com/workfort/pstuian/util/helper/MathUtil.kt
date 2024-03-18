package com.workfort.pstuian.util.helper

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

object MathUtil {
    inline fun <reified T>shuffle(array: ArrayList<T>) {
        var i = 0
        repeat(array.size) {
            val j = floor(Math.random() * (i + 1)).toInt()
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
            i++
        }
    }

    fun prettyCount(number: Number): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.00").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }
}