package com.example.aplikasibelajar.models

data class WarnaGames(
    var soal: String = "",
    var optionA: String = "",
    var optionB: String = "",
    var optionC: String = "",
    var optionD: String = "",
    var kodeSoal: Int = 0,
    var kodeOptionA: Int = 0,
    var kodeOptionB: Int = 0,
    var kodeOptionC: Int = 0,
    var kodeOptionD: Int = 0,
    var warnaSound : Int = 0
)