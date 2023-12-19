package com.example.aplikasibelajar.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.beruangbena.R
import com.example.beruangbena.data.BangunDatar
import kotlinx.android.synthetic.main.fragment_bangun_datar_home.*

class BangunDatarHomeFragment : Fragment(), View.OnClickListener {
    companion object {
        fun newInstance() = BangunDatarHomeFragment()
    }

    private var list: ArrayList<com.example.beruangbena.models.BangunDatar> = arrayListOf()
    private var i = 0
    private var j = 0
    private lateinit var audio : MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bangun_datar_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Load Item
        list.addAll(BangunDatar.listData)
        j = list.size
        //Set Onlick
        btnNext.setOnClickListener(this)
        btn_tapBentuk.setOnClickListener(this)
        img_bentuk.setOnClickListener(this)
        //Load Item
        loadItem(i)
    }

    private fun loadItem(i: Int) {
        img_bentuk.setImageResource(list[i].bangunDrawable)
        txt_namabentuk.text = list[i].bangunTtitle
        audio = MediaPlayer.create(view?.context, list[i].bangunSound)
        audio.start()
    }

    override fun onClick(v: View?) {
        i += 1
        if (i == j) {
            i = 0
            loadItem(i)
        } else
            loadItem(i)
    }

}