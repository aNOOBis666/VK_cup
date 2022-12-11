package com.delema.vk_cup.utils

fun <T>Boolean.then(yes: () -> T, no: () -> T): T {
    return if (this) yes()
    else no()
}

fun Boolean.toInt(): Int = if (this) 1 else 0