package com.example.aplikasibelajar.ui.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aplikasibelajar.R
import kotlinx.android.synthetic.main.fragment_angka_home.*
import java.util.*

class AngkaHomeFragment : Fragment() {

    companion object {
        fun newInstance() = AngkaHomeFragment()
    }

    private lateinit var ost : MediaPlayer
    private lateinit var numberDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var imgAngka: ImageView
    private lateinit var textAngka: TextView
    private lateinit var audio: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_angka_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Deklarasi Number Dialog
        numberDialog = AlertDialog.Builder(view?.context).create()
        dialog = LayoutInflater.from(context).inflate(R.layout.alert_dialog_number_info, null)
        numberDialog.setCancelable(false)
        numberDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Number ViewBinding
        imgAngka = dialog.findViewById(R.id.imageView3)
        textAngka = dialog.findViewById(R.id.textView5)
        //SetOnClicListener
        btn_zero.setOnClickListener {
            showNumberInfo(R.drawable.zero, "Nol", R.raw.nol)
        }
        btn_one.setOnClickListener {
            showNumberInfo(R.drawable.one, "Satu", R.raw.satu)
        }
        btn_two.setOnClickListener {
            showNumberInfo(R.drawable.two, "Dua", R.raw.dua)
        }
        btn_three.setOnClickListener {
            showNumberInfo(R.drawable.three, "Tiga", R.raw.tiga)
        }
        btn_four.setOnClickListener {
            showNumberInfo(R.drawable.four, "Empat", R.raw.empat)
        }
        btn_five.setOnClickListener {
            showNumberInfo(R.drawable.five, "Lima", R.raw.lima)
        }
        btn_six.setOnClickListener {
            showNumberInfo(R.drawable.six, "Enam", R.raw.enam)
        }
        btn_seven.setOnClickListener {
            showNumberInfo(R.drawable.seven, "Tujuh", R.raw.tujuh)
        }
        btn_eight.setOnClickListener {
            showNumberInfo(R.drawable.eight, "Delapan", R.raw.delapan)
        }
        btn_nine.setOnClickListener {
            showNumberInfo(R.drawable.nine, "Sembilan", R.raw.sembilan)
        }
        ost =  MediaPlayer.create(view?.context, R.raw.tekan_angka)
        ost.start()
    }

    private fun showNumberInfo(image: Int, name: String, audioSrc: Int) {
        imgAngka.setImageResource(image)
        textAngka.text = name
        audio = MediaPlayer.create(view?.context, audioSrc)
        audio.start()
        //Show Alert Dialog
        numberDialog.setView(dialog)
        numberDialog.show()
        val t = Timer()
        t.schedule(object : TimerTask() {
            override fun run() {
                numberDialog.dismiss()
                t.cancel()
            }
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        ost.stop()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        ost.stop()
    }
}