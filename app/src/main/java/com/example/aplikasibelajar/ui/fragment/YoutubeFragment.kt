package com.example.aplikasibelajar.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.adapter.AdapterYoutube
import com.example.aplikasibelajar.models.Youtube
import com.example.aplikasibelajar.utils.BackgroundServices
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_youtube.*
import kotlinx.android.synthetic.main.fragment_youtube.img_bear

class YoutubeFragment : Fragment() {

    companion object {
        fun newInstance() = YoutubeFragment()
    }

    private var list: ArrayList<Youtube> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //LoadItem
        rvYoutube.setHasFixedSize(true)
        list.addAll(com.example.aplikasibelajar.data.Youtube.listData)
        //Load Item to RecyclerView
        rvYoutube.layoutManager = LinearLayoutManager(view?.context)
        val adapterYT = AdapterYoutube(list)
        rvYoutube.adapter = adapterYT

        //Deklarasi animasi
        val animScale = AnimationUtils.loadAnimation(view?.context, R.anim.scale)
        val animTopToButton = AnimationUtils.loadAnimation(view?.context, R.anim.toptobutton)
        //Menerapkan Animasi
        img_bear.animation = animTopToButton
        belajarangka.animation = animTopToButton
        rvYoutube.animation = animScale
    }

    override fun onResume() {
        super.onResume()
        view?.context?.startService(Intent(view?.context, BackgroundServices::class.java))
    }
}