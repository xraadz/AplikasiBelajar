package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.Warna

object Warna {
    private val warnaTitle = arrayOf(
        "Merah",
        "Kuning",
        "Biru",
        "Hijau",
        "Hitam",
        "Kelabu",
        "Oranye",
        "Ungu",
        "Putih",
        "Merah Muda"
    )

    private val warnaCode = intArrayOf(
        R.color.Merah,
        R.color.Kuning,
        R.color.Biru,
        R.color.Hijau,
        R.color.hitam,
        R.color.Kelabu,
        R.color.Oranye,
        R.color.Ungu,
        R.color.Putih,
        R.color.Pink
    )

    private val warnaAudio = intArrayOf(
        R.raw.merah,
        R.raw.kuning,
        R.raw.biru,
        R.raw.hijau,
        R.raw.hitam,
        R.raw.kelabu,
        R.raw.oranye,
        R.raw.ungu,
        R.raw.putih,
        R.raw.pink
    )

    val listData: ArrayList<Warna>
        get() {
            val list = arrayListOf<Warna>()
            for (position in warnaTitle.indices) {
                val warna = Warna()
                warna.nama = warnaTitle[position]
                warna.kode = warnaCode[position]
                warna.audio = warnaAudio[position]
                list.add(warna)
            }
            return list
        }
}