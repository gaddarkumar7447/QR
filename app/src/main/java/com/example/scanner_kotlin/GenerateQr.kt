package com.example.scanner_kotlin

import android.R.attr.bitmap
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.scanner_kotlin.databinding.ActivityGenerateQrBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder


class GenerateQr : AppCompatActivity() {
    var flag = 0
    lateinit var dataBinding: ActivityGenerateQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_generate_qr)

        dataBinding.generateBn.setOnClickListener(View.OnClickListener {
            val string = dataBinding.inputEt.text.toString()
            if (string.isEmpty()) {
                dataBinding.inputEt.error = "write here something"
            } else {
                flag = 1
                val multiFormatWriter = MultiFormatWriter()
                val bitMatrix = multiFormatWriter.encode(string, BarcodeFormat.QR_CODE, 350, 350)
                val barcodeEncoder = BarcodeEncoder()
                val bitMap = barcodeEncoder.createBitmap(bitMatrix)
                dataBinding.outputIv.setImageBitmap(bitMap)

                val manager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(dataBinding.inputEt.applicationWindowToken, 0)

            }
        })

        dataBinding.saveGalleryBn.setOnClickListener(View.OnClickListener {
            if (flag ==1){
                val bitMap = dataBinding.outputIv.drawable as BitmapDrawable
                val bit = bitMap.bitmap

                /*val bitmapPath: String = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "QR code", "share QR code")*/
                // Save with location, value, bitmap returned and type of Image(JPG/PNG).
                // Save with location, value, bitmap returned and type of Image(JPG/PNG).

                val bitmapUri: Uri = Uri.parse(bit.toString())

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "generate first", Toast.LENGTH_SHORT).show()
            }
        })

    }
}