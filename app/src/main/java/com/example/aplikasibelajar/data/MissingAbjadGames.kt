package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.MissingAbjadGames

object MissingAbjadGames {
    private val soal1 = intArrayOf(
        R.drawable.a,
        R.drawable.missing,
        R.drawable.p,
        R.drawable.k,
        R.drawable.t,
        R.drawable.r,
        R.drawable.e,
        R.drawable.missing,
        R.drawable.h
    )

    private val soal2 = intArrayOf(
        R.drawable.missing,
        R.drawable.h,
        R.drawable.q,
        R.drawable.l,
        R.drawable.u,
        R.drawable.s,
        R.drawable.f,
        R.drawable.p,
        R.drawable.i
    )

    private val soal3 = intArrayOf(
        R.drawable.c,
        R.drawable.i,
        R.drawable.r,
        R.drawable.missing,
        R.drawable.v,
        R.drawable.t,
        R.drawable.g,
        R.drawable.q,
        R.drawable.missing
    )

    private val soal4 = intArrayOf(
        R.drawable.d,
        R.drawable.j,
        R.drawable.missing,
        R.drawable.n,
        R.drawable.w,
        R.drawable.missing,
        R.drawable.missing,
        R.drawable.r,
        R.drawable.k
    )

    private val soal5 = intArrayOf(
        R.drawable.e,
        R.drawable.k,
        R.drawable.t,
        R.drawable.o,
        R.drawable.missing,
        R.drawable.v,
        R.drawable.i,
        R.drawable.s,
        R.drawable.l
    )

    private val kunjaw = intArrayOf(
        R.drawable.b,
        R.drawable.g,
        R.drawable.s,
        R.drawable.m,
        R.drawable.x,
        R.drawable.u,
        R.drawable.h,
        R.drawable.o,
        R.drawable.j
    )

    private val jaw1 = intArrayOf(
        R.drawable.c,
        R.drawable.a,
        R.drawable.t,
        R.drawable.o,
        R.drawable.s,
        R.drawable.p,
        R.drawable.a,
        R.drawable.a,
        R.drawable.m
    )

    private val jaw2 = intArrayOf(
        R.drawable.b,
        R.drawable.i,
        R.drawable.w,
        R.drawable.p,
        R.drawable.c,
        R.drawable.l,
        R.drawable.i,
        R.drawable.o,
        R.drawable.l
    )

    private val jaw3 = intArrayOf(
        R.drawable.e,
        R.drawable.g,
        R.drawable.l,
        R.drawable.h,
        R.drawable.x,
        R.drawable.n,
        R.drawable.j,
        R.drawable.q,
        R.drawable.n
    )

    private val jaw4 = intArrayOf(
        R.drawable.f,
        R.drawable.f,
        R.drawable.s,
        R.drawable.j,
        R.drawable.z,
        R.drawable.u,
        R.drawable.c,
        R.drawable.p,
        R.drawable.i
    )

    private val jaw5 = intArrayOf(
        R.drawable.i,
        R.drawable.h,
        R.drawable.u,
        R.drawable.m,
        R.drawable.y,
        R.drawable.o,
        R.drawable.k,
        R.drawable.k,
        R.drawable.j
    )

    private val jaw6 = intArrayOf(
        R.drawable.a,
        R.drawable.e,
        R.drawable.p,
        R.drawable.r,
        R.drawable.l,
        R.drawable.v,
        R.drawable.h,
        R.drawable.l,
        R.drawable.o
    )

    val listData: ArrayList<MissingAbjadGames>
        get() {
            val list = arrayListOf<MissingAbjadGames>()
            for (position in soal1.indices) {
                val abjad = MissingAbjadGames()
                abjad.soal1 = soal1[position]
                abjad.soal2 = soal2[position]
                abjad.soal3 = soal3[position]
                abjad.soal4 = soal4[position]
                abjad.soal5 = soal5[position]
                abjad.kunjaw = kunjaw[position]
                abjad.jaw1 = jaw1[position]
                abjad.jaw2 = jaw2[position]
                abjad.jaw3 = jaw3[position]
                abjad.jaw4 = jaw4[position]
                abjad.jaw5 = jaw5[position]
                abjad.jaw6 = jaw6[position]
                list.add(abjad)
            }
            return list
        }
}