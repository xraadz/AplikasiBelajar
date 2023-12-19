package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.BangunDatar

object BangunDatar {
    private val bangunTtitle = arrayOf(
        "Belah Ketupat",
        "Layang - Layang",
        "Lingkaran",
        "Persegi Panjang",
        "Segi Enam",
        "Segi Lima",
        "Segitiga",
        "Trapesium",
        "Persegi"
    )

    private val bangunDrawable = intArrayOf(
        R.drawable.ketupat,
        R.drawable.layanglayang2,
        R.drawable.lingkaran,
        R.drawable.persegipanjang,
        R.drawable.segienam,
        R.drawable.segilima,
        R.drawable.segitiga,
        R.drawable.trapesium,
        R.drawable.persegi
    )

    private val bangunSuara = intArrayOf(
        R.raw.belahketupat,
        R.raw.layanglayang,
        R.raw.lingkaran,
        R.raw.persegipanjang,
        R.raw.segienam,
        R.raw.segilima,
        R.raw.segitiga,
        R.raw.trapesium,
        R.raw.persegi
    )

    val listData: ArrayList<BangunDatar>
        get() {
            val list = arrayListOf<BangunDatar>()
            for (position in bangunTtitle.indices) {
                val bangun = BangunDatar()
                bangun.bangunTtitle = bangunTtitle[position]
                bangun.bangunDrawable = bangunDrawable[position]
                bangun.bangunSound = bangunSuara[position]
                list.add(bangun)
            }
            return list
        }
}