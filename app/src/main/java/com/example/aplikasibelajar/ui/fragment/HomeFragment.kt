package com.example.aplikasibelajar.ui.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.ui.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener {


    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var tap: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_warna.setOnClickListener(this)
        btn_bentuk.setOnClickListener(this)
        btn_angka.setOnClickListener(this)
        btn_huruf.setOnClickListener(this)
        btnCamera.setOnClickListener(this)
        //Deklarasi animasi
        val animScale = AnimationUtils.loadAnimation(view?.context, R.anim.scale)
        val animTopToButton = AnimationUtils.loadAnimation(view?.context, R.anim.toptobutton)
        //Menerapkan Animasi
        img_bear.animation = animTopToButton
        txtBear.animation = animTopToButton
        btn_huruf.animation = animScale
        btn_warna.animation = animScale
        btn_angka.animation = animScale
        btn_bentuk.animation = animScale
    }

    private fun playSound() {
        tap = MediaPlayer.create(view?.context, R.raw.tap_button)
        tap.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_warna -> {
                playSound()
                view?.context?.startActivity(
                    Intent(
                        view?.context,
                        WarnaActivity::class.java
                    )
                )
            }
            R.id.btn_bentuk -> {
                playSound()
                view?.context?.startActivity(
                    Intent(
                        view?.context,
                        BangunDatarActivity::class.java
                    )
                )
            }
            R.id.btn_angka -> {
                playSound()
                view?.context?.startActivity(
                    Intent(
                        view?.context,
                        AngkaActivity::class.java
                    )
                )
            }
            R.id.btn_huruf -> {
                playSound()
                view?.context?.startActivity(
                    Intent(view?.context, HurufActivity::class.java)
                )
            }
            R.id.btnCamera -> {
                playSound()
                view?.context?.startActivity(
                    Intent(view?.context, SmartCameraActivity::class.java)
                )
            }
        }
    }

}