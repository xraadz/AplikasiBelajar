package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.WarnaGames

object WarnaGames {
    private val warnaSoal = arrayOf(
        "Merah",
        "Hitam",
        "Putih",
        "Biru",
        "Kuning",
        "Hijau",
        "Ungu",
        "Oranye",
        "Merah Muda",
        "Kelabu"
    )

    private val warnaSound = intArrayOf(
        R.raw.pertanyaan_warna_merah,
        R.raw.pertanyaan_warna_hitam,
        R.raw.pertanyaan_warna_putih,
        R.raw.pertanyaan_warna_biru,
        R.raw.pertanyaan_warna_kuning,
        R.raw.pertanyaan_warna_hijau,
        R.raw.pertanyaan_warna_ungu,
        R.raw.pertanyaan_warna_orange,
        R.raw.pertanyaan_warna_merahmuda,
        R.raw.pertanyaan_warna_kelabu
    )

    private val optioanA = arrayOf(
        "Kuning",
        "Putih",
        "Hitam",
        "Biru",
        "Kuning",
        "Hijau",
        "Oranye",
        "Merah",
        "Hitam",
        "Merah Muda"
    )

    private val optionB = arrayOf(
        "Merah",
        "Hitam",
        "Putih",
        "Kuning",
        "Hijau",
        "Biru",
        "Kelabu",
        "Biru",
        "Putih",
        "Merah"
    )

    private val optionC = arrayOf(
        "Biru",
        "Kelabu",
        "Biru",
        "Hijau",
        "Biru",
        "Kuning",
        "Ungu",
        "Oranye",
        "Merah",
        "Biru"
    )

    private val optionD = arrayOf(
        "Hijau",
        "Merah Muda",
        "Merah",
        "Merah Muda",
        "Merah",
        "Ungu",
        "Merah",
        "Merah Muda",
        "Merah Muda",
        "Kelabu"
    )

    private val kodeSoal = intArrayOf(
        R.color.Merah,
        R.color.hitam,
        R.color.Putih,
        R.color.Biru,
        R.color.Kuning,
        R.color.Hijau,
        R.color.Ungu,
        R.color.Oranye,
        R.color.Pink,
        R.color.Kelabu
    )

    private val kodeOptionA = intArrayOf(
        R.drawable.bananasyellow,
        R.drawable.strawberry_white,
        R.drawable.blueberryblack,
        R.drawable.blueberryblue,
        R.drawable.bananasyellow,
        R.drawable.avocadogreen,
        R.drawable.mango,
        R.drawable.apelred,
        R.drawable.blueberryblack,
        R.drawable.passion_fruit
    )

    private val kodeOptionB = intArrayOf(
        R.drawable.apelred,
        R.drawable.blueberryblack,
        R.drawable.strawberry_white,
        R.drawable.bananasyellow,
        R.drawable.avocadogreen,
        R.drawable.blueberryblue,
        R.drawable.coconutgrey,
        R.drawable.blueberryblue,
        R.drawable.strawberry_white,
        R.drawable.apelred
    )

    private val kodeOptionC = intArrayOf(
        R.drawable.blueberryblue,
        R.drawable.coconutgrey,
        R.drawable.blueberryblue,
        R.drawable.avocadogreen,
        R.drawable.blueberryblue,
        R.drawable.bananasyellow,
        R.drawable.grapesviolet,
        R.drawable.mango,
        R.drawable.apelred,
        R.drawable.blueberryblue
    )

    private val kodeOptionD = intArrayOf(
        R.drawable.avocadogreen,
        R.drawable.passion_fruit,
        R.drawable.apelred,
        R.drawable.passion_fruit,
        R.drawable.apelred,
        R.drawable.grapesviolet,
        R.drawable.apelred,
        R.drawable.passion_fruit,
        R.drawable.passion_fruit,
        R.drawable.coconutgrey
    )

    val listData: ArrayList<WarnaGames>
        get() {
            val list = arrayListOf<WarnaGames>()
            for (position in warnaSoal.indices) {
                val warna = com.example.aplikasibelajar.models.WarnaGames()
                warna.soal = warnaSoal[position]
                warna.kodeOptionA = kodeOptionA[position]
                warna.kodeOptionB = kodeOptionB[position]
                warna.kodeOptionC = kodeOptionC[position]
                warna.kodeOptionD = kodeOptionD[position]
                warna.optionA = optioanA[position]
                warna.optionB = optionB[position]
                warna.optionC = optionC[position]
                warna.optionD = optionD[position]
                warna.kodeSoal = kodeSoal[position]
                warna.warnaSound = warnaSound[position]
                list.add(warna)
            }
            return list
        }
}