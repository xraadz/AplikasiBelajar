package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.HurufGames

object AbjadGames {

    private val abjadSoal = arrayOf(
        "GIT_R",
        "MO_IL",
        "_INGA",
        "LAP_OP",
        "P_H_N",
        "GU_U_G",
        "KAL_ND_R",
        "M_K_N_N",
        "_INU_AN",
        "KEL_P_"
    )

    private val abjadGambar = intArrayOf(
        R.drawable.guitar,
        R.drawable.jeep,
        R.drawable.lion,
        R.drawable.laptop,
        R.drawable.tree,
        R.drawable.mountain,
        R.drawable.calendar,
        R.drawable.food,
        R.drawable.drink,
        R.drawable.coconut
    )
    private val abjadJawaban = intArrayOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.s,
        R.drawable.t,
        R.drawable.o,
        R.drawable.n,
        R.drawable.e,
        R.drawable.a,
        R.drawable.m,
        R.drawable.a
    )

    private val abjadA = intArrayOf(
        R.drawable.o,
        R.drawable.b,
        R.drawable.b,
        R.drawable.p,
        R.drawable.u,
        R.drawable.m,
        R.drawable.i,
        R.drawable.u,
        R.drawable.a,
        R.drawable.l
    )

    private val abjadB = intArrayOf(
        R.drawable.i,
        R.drawable.a,
        R.drawable.x,
        R.drawable.j,
        R.drawable.o,
        R.drawable.k,
        R.drawable.o,
        R.drawable.o,
        R.drawable.t,
        R.drawable.e
    )

    private val abjadC = intArrayOf(
        R.drawable.p,
        R.drawable.i,
        R.drawable.c,
        R.drawable.t,
        R.drawable.e,
        R.drawable.n,
        R.drawable.e,
        R.drawable.p,
        R.drawable.n,
        R.drawable.o
    )

    private val abjadD = intArrayOf(
        R.drawable.a,
        R.drawable.t,
        R.drawable.s,
        R.drawable.a,
        R.drawable.a,
        R.drawable.a,
        R.drawable.a,
        R.drawable.i,
        R.drawable.o,
        R.drawable.a
    )

    private val abjadE = intArrayOf(
        R.drawable.r,
        R.drawable.p,
        R.drawable.a,
        R.drawable.i,
        R.drawable.i,
        R.drawable.o,
        R.drawable.u,
        R.drawable.c,
        R.drawable.m,
        R.drawable.p
    )

    private val abjadF = intArrayOf(
        R.drawable.e,
        R.drawable.d,
        R.drawable.i,
        R.drawable.u,
        R.drawable.p,
        R.drawable.u,
        R.drawable.k,
        R.drawable.a,
        R.drawable.l,
        R.drawable.k
    )
    val listData: ArrayList<HurufGames>
        get() {
            val list = arrayListOf<HurufGames>()
            for (position in abjadSoal.indices) {
                val huruf = HurufGames()
                huruf.abjadSoal = abjadSoal[position]
                huruf.abjadGambar = abjadGambar[position]
                huruf.abjadJawaban = abjadJawaban[position]
                huruf.abjadA = abjadA[position]
                huruf.abjadB = abjadB[position]
                huruf.abjadC = abjadC[position]
                huruf.abjadD = abjadD[position]
                huruf.abjadE = abjadE[position]
                huruf.abjadF = abjadF[position]
                list.add(huruf)
            }
            return list
        }

}