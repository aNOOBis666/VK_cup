package com.delema.vk_cup.utils

import android.view.View

fun View.visible(show: Boolean) {
    show.then(
        { visibility = View.VISIBLE },
        { visibility = View.GONE }
    )
}