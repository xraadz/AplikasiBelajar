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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.WarnaGames
import com.example.aplikasibelajar.ui.SummaryActivity
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_warna_games.*
import java.util.*

class WarnaGamesFragment : Fragment() {
    companion object {
        fun newInstance() = WarnaGamesFragment()
    }

    private lateinit var sessionManager: SessionManager
    private var i = 0
    private var j = 1
    private var salah = 0
    private var counterSalah = 0
    private var countBenar = 0
    private var list: ArrayList<WarnaGames> = arrayListOf()
    private lateinit var alertDialog: AlertDialog
    private lateinit var rightDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var btnCobaLagi: Button
    private lateinit var textAnswer: TextView
    private lateinit var audio: MediaPlayer
    private lateinit var jawBenar : MediaPlayer
    private lateinit var jawSalah : MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_warna_games, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Deklarasi Session
        sessionManager = SessionManager(view!!.context)
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
        //Load and shuffle item
        list.addAll(com.example.beruangbena.data.WarnaGames.listData)
        list.shuffle()
        j = list.size
        //Load Soal
        loadSoal(i)
        jawBenar = MediaPlayer.create(view?.context, R.raw.jawaban_benar)
        jawSalah = MediaPlayer.create(view?.context, R.raw.jawaban_salah)
    }

    private fun loadSoal(i: Int) {
        audio = MediaPlayer.create(view?.context, list[i].warnaSound)
        audio.start()
        btn_bulaSoal.backgroundTintList =
            view?.context?.let { ContextCompat.getColorStateList(it, list[i].kodeSoal) }
        txt_pilih.text = """Pilih buah mana yang berwarna ${list[i].soal}"""
        btn_bulatMerah?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].kodeOptionA) }
        btn_bulatHitam?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].kodeOptionB) }
        btn_bulatHijau?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].kodeOptionC) }
        btn_bulatBiru?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].kodeOptionD) }
        //SetOnclickListener untuk pengecekan jawaban
        btn_bulatMerah.setOnClickListener {
            validation(list[i].soal, list[i].optionA)
        }
        btn_bulatHitam.setOnClickListener {
            validation(list[i].soal, list[i].optionB)
        }
        btn_bulatHijau.setOnClickListener {
            validation(list[i].soal, list[i].optionC)
        }
        btn_bulatBiru.setOnClickListener {
            validation(list[i].soal, list[i].optionD)
        }
    }

    private fun validation(answer: String, option: String) {
        audio.stop()
        if (answer == option) {
            //Jika Jawaban Benar
            jawBenar.start()
            sessionManager.putIsInGame(true)
            this@WarnaGamesFragment.i += 1
            countBenar += 1
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
            counterSalah += 1
            //Set Text On Alert Dialog
            textAnswer.text = "Buah yang kamu pilih berwarna $option"
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
            sessionManager.putBoolean("Warna", true)
            sessionManager.putIsInGame(false)
            val intent = Intent(view?.context, SummaryActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("jumSalah", counterSalah)
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