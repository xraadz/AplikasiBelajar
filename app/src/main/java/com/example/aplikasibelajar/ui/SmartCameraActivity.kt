package com.example.aplikasibelajar.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import clarifai2.api.ClarifaiBuilder
import clarifai2.dto.input.ClarifaiInput
import clarifai2.dto.model.output.ClarifaiOutput
import clarifai2.dto.prediction.Color
import com.example.aplikasibelajar.R
import kotlinx.android.synthetic.main.activity_smart_camera.*
import java.io.File
import java.io.IOException


class SmartCameraActivity : AppCompatActivity(), View.OnClickListener {
    private var requestPhotoPath = 1
    private lateinit var photoPath: String
    lateinit var pDialog: ProgressDialog

    private class ClarifaiTask constructor(activity: Activity) : AsyncTask<File, Int, Void>() {
        lateinit var result: List<ClarifaiOutput<Color>>
        private var activity: Activity = activity

        override fun onPostExecute(void: Void?) {
            super.onPostExecute(void)
            var value = 0F;
            var name = ""
            var info: TextView = activity.findViewById(R.id.txtInfo)
            info.text = ""
            for (item in result) {
                Log.d("Item : ", "${item.data()}")
                for (datum in item.data()) {
                    if (datum.value() > value) {
                        value = datum.value()
                        name = datum.webSafeColorName()
                    }
                    info.append("${datum.webSafeColorName()} : ${datum.value()} : ${datum.hex()} \n")
                }
            }
            info.append("Dari hasil prediksi, foto adalah : $name dengan tingkat akurat $value")

        }

        override fun doInBackground(vararg images: File?): Void? {
            var info: TextView = activity.findViewById(R.id.txtInfo)
            try {
                info.text = "Harap Tunggu..."
                var clarifai = ClarifaiBuilder("043846f771f645099540a07dd1e26b9b").buildSync()
                for (image in images) {
                    result = clarifai.defaultModels.colorModel().predict()
                        .withInputs(ClarifaiInput.forImage(image!!))
                        .executeSync()
                        .get()
                    Log.d("Result", "$result")
                }
            } catch (error: Exception) {
                info.text = "Terjadi Error : $error"
                Log.d("error", "Error : $error")
            }
            return null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_camera)
        pDialog = ProgressDialog(applicationContext)
        pDialog.setTitle("Harap Tunggu...")
        btnAmbilGambar.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestPhotoPath) {
            if (resultCode == RESULT_OK)
                if (photoPath != null) {
                    val myBitmap = BitmapFactory.decodeFile(photoPath)
                    imgPreview.setImageBitmap(myBitmap)
                    ClarifaiTask(this).execute(File(photoPath))
                }

        }
    }

    private fun takePicture() {
        var takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File
            try {
                var storageDir = filesDir
                photoFile = File.createTempFile("SNAPSHOT", ".jpg", storageDir)
                photoPath = photoFile.absolutePath
            } catch (error: IOException) {
                return
            }
            var photoUri = FileProvider.getUriForFile(
                this,
                "com.example.beruangbena.fileprovider",
                photoFile
            )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, requestPhotoPath)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnAmbilGambar -> {
                takePicture()
            }
        }
    }
}