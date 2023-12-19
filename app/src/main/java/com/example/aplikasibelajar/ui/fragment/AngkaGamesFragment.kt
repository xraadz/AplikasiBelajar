package com.example.aplikasibelajar.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.adapter.AdapterAngkaGames
import com.example.aplikasibelajar.data.AngkaGames
import com.example.aplikasibelajar.models.Beruang
import com.example.aplikasibelajar.ui.SummaryActivity
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_angka_games.*
import java.util.*

class AngkaGamesFragment : Fragment() {

    companion object {
        fun newInstance() = AngkaGamesFragment()
    }

    private var list: ArrayList<com.example.aplikasibelajar.models.AngkaGames> = arrayListOf()
    private var i = 0
    private var j = 0
    private var listAngka: ArrayList<Beruang> = arrayListOf()
    private var salah = 0
    private var countSalah = 0
    private var countBenar = 0
    private lateinit var alertDialog: AlertDialog
    private lateinit var rightDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var btnCobaLagi: Button
    private lateinit var textAnswer: TextView
    private lateinit var session: SessionManager
    private lateinit var audio: MediaPlayer
    private lateinit var jawBenar: MediaPlayer
    private lateinit var jawSalah: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_angka_games, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Load Item
        rv_beruang.setHasFixedSize(true)
        list.addAll(AngkaGames.listData)
        list.shuffle()
        j = list.size
        //session
        session = SessionManager(view!!.context)
        //Deklarasi Right Dialog
        rightDialog = AlertDialog.Builder(view?.context).create()
        rightDialog.setView(
            LayoutInflater.from(context).inflate(R.layout.alert_dialog_right_answer, null)
        )
        rightDialog.setCancelable(false)
        rightDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Deklarasi AlertDialog
        alertDialog = AlertDialog.Builder(view?.context).create()
        dialog = LayoutInflater.from(context).inflate(R.layout.alert_dialog_wrong_answer, null)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //AlertDialog ViewBinding
        btnCobaLagi = dialog.findViewById(R.id.btnCobaLagi)
        textAnswer = dialog.findViewById(R.id.textView6)
        btnCobaLagi.setOnClickListener {
            alertDialog.hide()
        }
        //load soal
        loadSoal(i)
        //OST
        jawBenar = MediaPlayer.create(view?.context, R.raw.jawaban_benar)
        jawSalah = MediaPlayer.create(view?.context, R.raw.jawaban_salah)
    }

    private fun loadSoal(i: Int) {
        audio = MediaPlayer.create(view?.context, R.raw.pertanyaan_jumlah_beruang)
        audio.start()
        //Membersihkan list
        listAngka.clear()
        //Looping untuk membuat item jumlah beruang
        for (a in 1..list[i].angkaNumber) {
            val beruang = Beruang()
            beruang.angka = list[i].angkaNumber
            listAngka.add(beruang)
        }
        //Load Item to recyclerView
        rv_beruang.layoutManager = GridLayoutManager(view?.context, 4)
        val adapterBeruang = AdapterAngkaGames(listAngka)
        rv_beruang.adapter = adapterBeruang
        //SetOnClick Button untuk pengecekan jawaban
        btn_zero.setOnClickListener {
            validation("Nol", list[i].angkaSoal)
        }
        btn_one.setOnClickListener {
            validation("Satu", list[i].angkaSoal)
        }
        btn_two.setOnClickListener {
            validation("Dua", list[i].angkaSoal)
        }
        btn_three.setOnClickListener {
            validation("Tiga", list[i].angkaSoal)
        }
        btn_four.setOnClickListener {
            validation("Empat", list[i].angkaSoal)
        }
        btn_five.setOnClickListener {
            validation("Lima", list[i].angkaSoal)
        }
        btn_six.setOnClickListener {
            validation("Enam", list[i].angkaSoal)
        }
        btn_seven.setOnClickListener {
            validation("Tujuh", list[i].angkaSoal)
        }
        btn_eight.setOnClickListener {
            validation("Delapan", list[i].angkaSoal)
        }
        btn_nine.setOnClickListener {
            validation("Sembilan", list[i].angkaSoal)
        }
    }

    private fun validation(answer: String, option: String) {
        audio.stop()
        if (answer == option) {
            //Jika Jawaban Benar
            jawBenar.start()
            countBenar += 1
            session.putIsInGame(true)
            this@AngkaGamesFragment.i += 1
            //Menampilkan Dialog
            rightDialog.show()
            val t = Timer()
            t.schedule(object : TimerTask() {
                override fun run() {
                    rightDialog.dismiss()
                    t.cancel()
                    Handler(Looper.getMainLooper()).post(Runnable {
                        checkQuestionNumber()
                    })
                }
            }, 2000)
        } else {
            //Jika Jawaban salah
            jawSalah.start()
            salah += 5
            countSalah += 1
            //Set Text On Alert Dialog
            textAnswer.text = "Jumlah beruang diigambar bukanlah $answer"
            //Show Alert Dialog
            alertDialog.setView(dialog)
            alertDialog.show()
        }
    }

    private fun checkQuestionNumber() {
        if (i == j) {
            var score = 100
            if (salah > score) score = 0
            else score -= salah
            alertDialog.dismiss()
            rightDialog.dismiss()
            session.putIsInGame(false)
            session.putBoolean("Angka", true)
            val intent = Intent(view?.context, SummaryActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("jumSalah", countSalah)
            intent.putExtra("jumBenar", countBenar)
            startActivity(intent)
            activity?.finish()
        } else
            loadSoal(i)
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