package com.example.aplikasibelajar.models

data class BangunDatarGames(
    var soal: String = "",
    var gambarSoal: Int = 0,
    var soalPilihanA: String = "",
    var soalPilihanB: String = "",
    var soalPilihanC: String = "",
    var soalPilihanD: String = "",
    var gambarPilihanA: Int = 0,
    var gambarPilihanB: Int = 0,
    var gambarPilihanC: Int = 0,
    var gambarPilihanD: Int = 0
)