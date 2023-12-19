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
import com.example.aplikasibelajar.data.AbjadGames
import com.example.aplikasibelajar.models.HurufGames
import com.example.aplikasibelajar.ui.SummaryActivity
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_huruf_games.*
import java.util.*

class HurufFragmentGames : Fragment() {

    companion object {
        fun newInstance() = HurufFragmentGames()
    }

    private lateinit var sessionManager: SessionManager
    private var i = 0
    private var j = 1
    private var salah = 0
    private var counterSalah = 0
    private var countBenar = 0
    private var list: ArrayList<HurufGames> = arrayListOf()
    private lateinit var alertDialog: AlertDialog
    private lateinit var rightDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var btnCobaLagi: Button
    private lateinit var textAnswer: TextView
    private lateinit var jawSalah: MediaPlayer
    private lateinit var jawBenar: MediaPlayer
    private lateinit var ost: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_huruf_games, container, false)
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
        list.addAll(AbjadGames.listData)
        list.shuffle()
        j = list.size
        //Load Soal
        loadSoal(i)
        //
        jawBenar = MediaPlayer.create(view?.context, R.raw.jawaban_benar)
        jawSalah = MediaPlayer.create(view?.context, R.raw.jawaban_salah)
    }

    private fun loadSoal(i: Int) {
        ost = MediaPlayer.create(view?.context, R.raw.game_huruf)
        ost.start()
        //Change Image
        btn_pilihan1?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadA) }
        btn_pilihan2?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadB) }
        btn_pilihan3?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadC) }
        btn_pilihan4?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadD) }
        btn_pilihan5?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadE) }
        btn_pilihan6?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].abjadF) }
        img_benda.setImageResource(list[i].abjadGambar)
        txt_soal.text = list[i].abjadSoal
        //OnClick
        btn_pilihan1.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadA),
                list[i].abjadSoal
            )
        }
        btn_pilihan2.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadB),
                list[i].abjadSoal
            )
        }
        btn_pilihan3.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadC),
                list[i].abjadSoal
            )
        }
        btn_pilihan4.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadD),
                list[i].abjadSoal
            )
        }
        btn_pilihan5.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadE),
                list[i].abjadSoal
            )
        }
        btn_pilihan6.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].abjadJawaban),
                resources.getResourceEntryName(list[i].abjadF),
                list[i].abjadSoal
            )
        }
    }

    private fun validation(answer: String, option: String, question: String) {
        ost.stop()
        if (answer == option) {
            //Jika Jawaban Benar
            jawBenar.start()
            sessionManager.putIsInGame(true)
            this@HurufFragmentGames.i += 1
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
            val text = question.replace("_", option).toUpperCase(Locale.ROOT)
            textAnswer.text = "Kata yang kamu pilih menjadi $text"
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
            sessionManager.putBoolean("Abjad", true)
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
        ost.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        ost.stop()
    }
}