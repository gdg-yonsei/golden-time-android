package com.next.goldentime.util

import androidx.core.text.isDigitsOnly

fun String.toIntList(): List<Int> {
    return this.split(",").filter { it.isNotEmpty() && it.isDigitsOnly() }.map { it.toInt() }
}