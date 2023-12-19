package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.BangunDatarGames

object BangunDatarGames {

    val soalBangunDatar = arrayOf(
        "Lingkaran",
        "Segitiga",
        "Persegi Panjang",
        "Segi Lima",
        "Layang - Layang",
        "Persegi",
        "Segi Enam",
        "Trapesium",
        "Belah Ketupat"
    )

    val gambarSoal = intArrayOf(
        R.drawable.lingkaran,
        R.drawable.segitiga,
        R.drawable.persegipanjang,
        R.drawable.segilima,
        R.drawable.layanglayang2,
        R.drawable.persegi,
        R.drawable.segienam,
        R.drawable.trapesium,
        R.drawable.ketupat
    )

    val soalPilihanA = arrayOf(
        "Persegi Panjang",
        "Belah Ketupat",
        "Trapesium",
        "Segi Lima",
        "Persegi",
        "Segi Enam",
        "Segi Enam",
        "Trapesium",
        "Segi Enam"
    )

    val gambarPilihanA = intArrayOf(
        R.drawable.bus,
        R.drawable.kupat,
        R.drawable.campingtent,
        R.drawable.rumah,
        R.drawable.giftbox,
        R.drawable.cage,
        R.drawable.cage,
        R.drawable.campingtent,
        R.drawable.cage
    )

    val soalPilihanB = arrayOf(
        "Lingkaran",
        "Layang - Layang",
        "Persegi",
        "Trapesium",
        "Segitiga",
        "Persegi",
        "Segitiga",
        "Persegi",
        "Persegi"
    )

    val gambarPilihanB = intArrayOf(
        R.drawable.baby,
        R.drawable.kite,
        R.drawable.giftbox,
        R.drawable.campingtent,
        R.drawable.ruler,
        R.drawable.giftbox,
        R.drawable.ruler,
        R.drawable.giftbox,
        R.drawable.giftbox
    )

    val soalPilihanC = arrayOf(
        "Persegi",
        "Segitiga",
        "Persegi Panjang",
        "Layang - Layang",
        "Belah Ketupat",
        "Persegi Panjang",
        "Segi Lima",
        "Persegi Panjang",
        "Belah Ketupat"
    )

    val gambarPilihanC = intArrayOf(
        R.drawable.giftbox,
        R.drawable.ruler,
        R.drawable.bus,
        R.drawable.kite,
        R.drawable.kupat,
        R.drawable.bus,
        R.drawable.rumah,
        R.drawable.bus,
        R.drawable.kupat
    )

    val soalPilihanD = arrayOf(
        "Belah Ketupat",
        "Segi Lima",
        "Segi Enam",
        "Belah Ketupat",
        "Layang - Layang",
        "Belah Ketupat",
        "Trapesium",
        "Segi Enam",
        "Trapesium"
    )

    val gambarPilihanD = intArrayOf(
        R.drawable.kupat,
        R.drawable.rumah,
        R.drawable.cage,
        R.drawable.kupat,
        R.drawable.kite,
        R.drawable.kupat,
        R.drawable.campingtent,
        R.drawable.cage,
        R.drawable.campingtent
    )

    val listData: ArrayList<BangunDatarGames>
        get() {
            val list = arrayListOf<BangunDatarGames>()
            for (position in com.example.aplikasibelajar.data.BangunDatarGames.soalBangunDatar.indices) {
                val bangun = com.example.aplikasibelajar.models.BangunDatarGames()
                bangun.soal = soalBangunDatar[position]
                bangun.gambarSoal = gambarSoal[position]
                bangun.soalPilihanA = soalPilihanA[position]
                bangun.soalPilihanB = soalPilihanB[position]
                bangun.soalPilihanC = soalPilihanC[position]
                bangun.soalPilihanD = soalPilihanD[position]
                bangun.gambarPilihanA = gambarPilihanA[position]
                bangun.gambarPilihanB = gambarPilihanB[position]
                bangun.gambarPilihanC = gambarPilihanC[position]
                bangun.gambarPilihanD = gambarPilihanD[position]
                list.add(bangun)
            }
            return list
        }

}