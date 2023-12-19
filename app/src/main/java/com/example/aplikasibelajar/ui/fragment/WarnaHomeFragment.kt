package com.example.aplikasibelajar.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.adapter.AdapterWarna
import com.example.aplikasibelajar.models.Warna
import kotlinx.android.synthetic.main.fragment_warna_home.*

class WarnaHomeFragment : Fragment() {
    companion object {
        fun newInstance() = WarnaHomeFragment()
    }

    private var list: ArrayList<Warna> = arrayListOf()
    private lateinit var audio : MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_warna_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Load Item
        rv_warna.setHasFixedSize(true)
        list.addAll(com.example.aplikasibelajar.data.Warna.listData)
        //Load Item to recyclerView
        rv_warna.layoutManager = GridLayoutManager(view?.context, 2)
        val adapterWarna = AdapterWarna(list)
        rv_warna.adapter = adapterWarna
        audio = MediaPlayer.create(view?.context, R.raw.tekan_warna)
        audio.start()
    }

    override fun onPause() {
        super.onPause()
        audio.stop()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        audio.stop()
    }
}