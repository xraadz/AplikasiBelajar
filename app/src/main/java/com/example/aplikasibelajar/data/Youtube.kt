package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.Youtube

object Youtube {

    private val ytName = arrayOf(
        "Warna",
        "Bangun Datar",
        "Angka",
        "Abjad"
    )

    private val ytThumbnail = intArrayOf(
        R.drawable.rara,
        R.drawable.powerpuff,
        R.drawable.adventuretime,
        R.drawable.sonic
    )

    private val ytColor = intArrayOf(
        R.color.colorytbiru,
        R.color.colorytkuning,
        R.color.colorytpink,
        R.color.colorythijau
    )

    private val ytLink = arrayOf(
        "PZOUSuVsRNE",
        "dCbusJPqvvY",
        "JlJZEBWTYgo",
        "LYvO8eaWJNc"
    )

    val listData: ArrayList<Youtube>
        get() {
            val list = arrayListOf<Youtube>()
            for (position in ytName.indices) {
                val yt = Youtube()
                yt.ytName = ytName[position]
                yt.ytThumb = ytThumbnail[position]
                yt.ytColor = ytColor[position]
                yt.ytLink = ytLink[position]
                list.add(yt)
            }
            return list
        }
}