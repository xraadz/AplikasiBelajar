package com.example.aplikasibelajar.data

import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.Huruf

object Abjad {

    private val abjad = intArrayOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j,
        R.drawable.k,
        R.drawable.l,
        R.drawable.m,
        R.drawable.n,
        R.drawable.o,
        R.drawable.p,
        R.drawable.q,
        R.drawable.r,
        R.drawable.s,
        R.drawable.t,
        R.drawable.u,
        R.drawable.v,
        R.drawable.w,
        R.drawable.x,
        R.drawable.y,
        R.drawable.z
    )

    private val abjadSound = intArrayOf(
        R.raw.a,
        R.raw.b,
        R.raw.c,
        R.raw.d,
        R.raw.e,
        R.raw.f,
        R.raw.g,
        R.raw.h,
        R.raw.i,
        R.raw.j,
        R.raw.k,
        R.raw.l,
        R.raw.m,
        R.raw.n,
        R.raw.o,
        R.raw.p,
        R.raw.q,
        R.raw.r,
        R.raw.s,
        R.raw.t,
        R.raw.u,
        R.raw.v,
        R.raw.w,
        R.raw.x,
        R.raw.y,
        R.raw.z
    )
    val listData: ArrayList<Huruf>
        get() {
            val list = arrayListOf<Huruf>()
            for (position in abjad.indices) {
                val huruf = Huruf()
                huruf.gambarAbjad = abjad[position]
                huruf.soundAbjad = abjadSound[position]
                list.add(huruf)
            }
            return list
        }
}