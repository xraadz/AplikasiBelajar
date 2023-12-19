package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.models.AngkaGames

object AngkaGames {

    private val angkaSoal = arrayOf(
        "Satu",
        "Dua",
        "Tiga",
        "Empat",
        "Lima",
        "Enam",
        "Tujuh",
        "Delapan",
        "Sembilan"
    )

    private val angkaNumber = intArrayOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9
    )

    val listData: ArrayList<AngkaGames>
        get() {
            val list = arrayListOf<AngkaGames>()
            for (position in angkaSoal.indices) {
                val angka = AngkaGames()
                angka.angkaSoal = angkaSoal[position]
                angka.angkaNumber = angkaNumber[position]
                list.add(angka)
            }
            return list
        }
}