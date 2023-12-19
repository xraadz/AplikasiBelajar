package com.example.aplikasibelajar.adapter

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.Youtube
import com.example.aplikasibelajar.ui.YoutubeActivity
import com.example.aplikasibelajar.utils.SessionManager

class AdapterYoutube(val listYoutube: ArrayList<Youtube>) :
    RecyclerView.Adapter<AdapterYoutube.ViewHolder>() {

    private lateinit var tap: MediaPlayer
    private lateinit var session: SessionManager
    private lateinit var alertDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var btnCobaLagi: Button
    private lateinit var textAnswer: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_youtube, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, thumbnail, color, link) = listYoutube[position]
        session = SessionManager(holder.itemView.context)
        holder.btnYt.setImageResource(thumbnail)
        holder.btnYt.backgroundTintList =
            ContextCompat.getColorStateList(holder.itemView.context, color)
        holder.cvYt.setCardBackgroundColor(color)
        //Deklarasi AlertDialog
        alertDialog = AlertDialog.Builder(holder.itemView.context).create()
        dialog =
            LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_locked, null)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //AlertDialog ViewBinding
        btnCobaLagi = dialog.findViewById(R.id.btnCobaLagi)
        textAnswer = dialog.findViewById(R.id.textView6)
        btnCobaLagi.setOnClickListener {
            alertDialog.hide()
        }
        //
        holder.btnYt.setOnClickListener {
            //Play OST
            tap = MediaPlayer.create(holder.itemView.context, R.raw.tap_button)
            tap.start()
            if (name == "Warna") {
                if (session.getValueBoolean("Warna") == true) {
                    alertDialog.dismiss()
                    val i = Intent(holder.itemView.context, YoutubeActivity::class.java)
                    i.putExtra("video", link)
                    holder.itemView.context.startActivity(i)
                } else {
                    //Show Alert Dialog
                    textAnswer.text = "Selesaikan game $name untuk membuka video ini"
                    alertDialog.setView(dialog)
                    alertDialog.show()
                }
            } else if (name == "Bangun Datar") {
                if (session.getValueBoolean("Bangun Datar") == true) {
                    alertDialog.dismiss()
                    val i = Intent(holder.itemView.context, YoutubeActivity::class.java)
                    i.putExtra("video", link)
                    holder.itemView.context.startActivity(i)
                } else {
                    //Show Alert Dialog
                    textAnswer.text = "Selesaikan game $name untuk membuka video ini"
                    alertDialog.setView(dialog)
                    alertDialog.show()
                }
            } else if (name == "Angka") {
                if (session.getValueBoolean("Angka") == true) {
                    alertDialog.dismiss()
                    val i = Intent(holder.itemView.context, YoutubeActivity::class.java)
                    i.putExtra("video", link)
                    holder.itemView.context.startActivity(i)
                } else {
                    //Show Alert Dialog
                    textAnswer.text = "Selesaikan game $name untuk membuka video ini"
                    alertDialog.setView(dialog)
                    alertDialog.show()
                }
            } else if (name == "Abjad") {
                if (session.getValueBoolean("Abjad") == true) {
                    alertDialog.dismiss()
                    val i = Intent(holder.itemView.context, YoutubeActivity::class.java)
                    i.putExtra("video", link)
                    holder.itemView.context.startActivity(i)
                } else {
                    //Show Alert Dialog
                    textAnswer.text = "Selesaikan game $name untuk membuka video ini"
                    alertDialog.setView(dialog)
                    alertDialog.show()
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return listYoutube.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnYt: ImageButton = itemView.findViewById(R.id.btnYoutube)
        var cvYt: CardView = itemView.findViewById(R.id.cardViewYoutube)
    }
}
